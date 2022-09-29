package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder str = new StringBuilder();
        try(RandomAccessFile aFile = new RandomAccessFile(file.getPath(), "r");
            FileChannel inChannel = aFile.getChannel();){

            ByteBuffer buffer  = ByteBuffer.allocate(1024);
            while(inChannel.read(buffer) > 0){
                buffer.flip();
                for(int i = 0;i< buffer.limit();i++){
                    str.append((char)buffer.get());
                }

            }
            buffer.clear();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Map<String,String> m = new HashMap<>();
        String[] pairs = str.toString().split("\n");
        for(String item: pairs){
            String [] keyValue = item.split(": ");
            m.put(keyValue[0], keyValue[1]);
        }
        String name = m.get("Name");
        int age = Integer.parseInt(m.get("Name"));
        long phone = Long.parseLong(m.get("Phone"));
        String email = m.get("Email");
        return new Profile(name, age, email,phone);
    }
}
