package com.sajiblocked.assistme;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.sajiblocked.assistme.Memory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class Utilities {

    public static final String FILE_EXTENSION = ".bin";
    static FileOutputStream fos;
    static ObjectOutputStream oos;
    static FileInputStream fis;
    static ObjectInputStream ois;

    public static boolean saveMemory(Context context, Memory memory) {
        String fileName = String.valueOf(memory.getTime()) + ".bin";

        try {
            //fos = context.OpenFileOutput(fileName, context.MODE_PRIVATE);
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(memory);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Memory> getAllSavedMemories(Context context) {
        ArrayList<Memory>memories = new ArrayList<>(); //keeping memories here
        File filesDir = context.getFilesDir();
        ArrayList<String> memoryFiles = new ArrayList<>(); //keeping file names here

        for(String file : filesDir.list()) {
            if(file.endsWith(FILE_EXTENSION)) {
                memoryFiles.add(file);
            }
        }

        for(int i=0; i<memoryFiles.size(); i++) {
            try {
                fis = context.openFileInput(memoryFiles.get(i));
                ois = new ObjectInputStream(fis);

                memories.add((Memory) ois.readObject());

                ois.close();
                fis.close();

            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        return memories;
    }

    public static Memory getMemoryByName(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        Memory memory;
        if(file.exists()) {
            try {
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);
                memory = (Memory) ois.readObject();

                fis.close();
                ois.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            return memory;
        } else {
            return null;
        }
    }

    public static void deleteMemory(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir, fileName);

        if(file.exists()) {
            file.delete();
        }
    }
}
