package com.mediaplayer.videoplayer;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StremingServer {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/videos/list")
    public List<String> getVodesList(){
        String dir = "./src/main/resources/videos/";
        return Stream.of(new File(dir).listFiles())
            .filter(file -> !file.isDirectory())
            .map(File::getName)
            .collect(Collectors.toList());
        
    }

    @GetMapping("/video/{fileName}")
    public ResponseEntity<StreamingResponseBody> playVideo(@PathVariable String fileName,HttpServletRequest request) throws IOException {
        
        //create the video file and the varialbes to set the requested chunk of the video.
        File video = new File("./src/main/resources/videos/" + fileName);
        long length = video.length();
        long rangeStart = 0;
        long rangeEnd = length - 1;

        //check if the browser request a specific chunk of video
        String rangeHeader = request.getHeader("Range");
        if (rangeHeader != null) {
            //if the browser has sent a request, assing it's values to the defined variables
            String[] ranges = rangeHeader.substring("bytes=".length()).split("-");
            rangeStart = Long.parseLong(ranges[0]);
            if (ranges.length > 1) {
                rangeEnd = Long.parseLong(ranges[1]);
            }
        }

        //calculate the content length
        long contentLength = rangeEnd - rangeStart + 1;
        
        //starting a byte inputStream and place the the file poiner to the requested chunk of video
        //if the request specify only the start, contue sreaming the entire video
        final RandomAccessFile file = new RandomAccessFile(video, "r");
        file.seek(rangeStart);

        //creating the output stream to pass data to the socket by reading from the input stream.
        StreamingResponseBody responseBody = outputStream -> {
            try {
                byte[] buffer = new byte[32768];
                long remaining = contentLength;
                int read;
                while ((read = file.read(buffer, 0, (int) Math.min(buffer.length, remaining))) != -1 && remaining > 0) {
                    outputStream.write(buffer, 0, read);
                    remaining -= read;
                }
            } finally {
                file.close();
            }
        };

        //specifyng the http headers
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(rangeHeader != null ? 206 : 200)
            .contentType(MediaType.parseMediaType("video/mp4"))
            .header("Accept-Ranges", "bytes")
            .header("Content-Range", String.format("bytes %d-%d/%d", rangeStart, rangeEnd, length))
            .header("Content-Length", String.valueOf(contentLength));

        return builder.body(responseBody);
    }
}
