package jikaoTest.honorTest;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Music {
    public char value;
    public String type;
    public int hobby;

    public Music(char value, String type, int hobby) {
        this.value = value;
        this.type = type;
        this.hobby = hobby;
    }
}

public class MusicHobby {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.eolIsSignificant(true);

        //用两个变量存储上次播放的歌曲以及上次中断的歌曲的类型
        String pType = null;
        String bType = null;
        HashMap<String, HashMap<Character, Music>> typeMap = new HashMap<>();
        HashMap<Character, Music> musicMap = new HashMap<>();
        while (true) {
            int token = in.nextToken();
            if (token == StreamTokenizer.TT_EOL) {
                if (br.read() == '\n') {
                    break;
                }
            }
            char operate = in.sval.charAt(0);
            in.nextToken();
            char value = in.sval.charAt(5);
            //I操作
            if (operate == 'I') {
                in.nextToken();
                String musicType = in.sval;
                //如果map不存在当前音乐类型，则添加这个音乐类型，顺便把音乐加进去
                Music music = new Music(value, musicType, 0);
                if (!typeMap.containsKey(musicType)) {
                    typeMap.put(musicType, new HashMap<>());
                    typeMap.get(musicType).put(value, music);
                    musicMap.put(value, music);
                } else {    //如果存在，就把这个音乐加进去
                    typeMap.get(musicType).put(value, music);
                    musicMap.put(value, music);
                }
            }
            if (operate == 'P') {
                //如果与上一个播放完的音乐类型相同，则set中所有music的hobby值都+1
                if (musicMap.get(value).type.equals(pType) && !musicMap.get(value).type.equals("UnkownStyle")) {
                    addAll(typeMap.get(pType));
                    Music music = musicMap.get(value);
                    music.hobby = music.hobby + 2;
                    pType = music.type;

                } else {
                    Music music = musicMap.get(value);
                    music.hobby = music.hobby + 3;
                    pType = music.type;
                }
            }
            if (operate == 'B') {
                //如果与上一个中断的音乐类型相同，则set中所有music的hobby值都-1
                if (musicMap.get(value).type.equals(bType) && !musicMap.get(value).type.equals("UnkownStyle")) {
                    subAll(typeMap.get(bType));
                    Music music = musicMap.get(value);
                    music.hobby = music.hobby - 1;
                    bType = music.type;

                } else {
                    Music music = musicMap.get(value);
                    music.hobby = music.hobby - 2;
                    bType = music.type;
                }
            }
            in.nextToken();
        }
        //最后将所有的歌曲导出到优先级队列，然后在输出即可
        PriorityQueue<Music> musics = new PriorityQueue<>((o1, o2) -> o2.hobby - o1.hobby);
        for (Map.Entry<Character, Music> entry : musicMap.entrySet()) {
            musics.add(entry.getValue());
        }

        //依次输出
        while (!musics.isEmpty()) {
            Music music = musics.poll();
            System.out.println("Music" + music.value + " " + music.type);
        }
    }

    private static void subAll(HashMap<Character, Music> map) {
        map.forEach((k, v) -> {
            Music music = map.get(k);
            music.hobby = music.hobby - 1;
        });
    }

    private static void addAll(HashMap<Character, Music> map) {
        map.forEach((k, v) -> {
            Music music = map.get(k);
            music.hobby = music.hobby + 1;
        });
    }
}
