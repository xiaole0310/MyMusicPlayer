package com.example.howard.mp3player.Bean;

import java.util.List;

/**
 * Created by Howard on 2016/7/19.
 */
public class SearchBean {

    @Override
    public String toString() {
        return "SearchBean{" +
                "order='" + order + '\'' +
                ", error_code=" + error_code +
                ", song=" + song +
                ", album=" + album +
                ", artist=" + artist +
                '}';
    }

    /**
     * song : [{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏","artistname":"周传雄","control":"0000000000","songid":"2124144","has_mv":"1","encrypted_songid":"00062069700956a1c461L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏的心","artistname":"张宇","control":"0000000000","songid":"1292754","has_mv":"0","encrypted_songid":"120613b9d209561d1c09L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏","artistname":"云朵","control":"0000000000","songid":"7134797","has_mv":"0","encrypted_songid":"65066cde4d09561d38c8L"},{"bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","yyr_artist":"0","songname":"黄昏里","artistname":"邓丽君","control":"0000000000","songid":"130236599","has_mv":"0","encrypted_songid":"20077c340b7095623ad1bL"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏","artistname":"新雅室内乐","control":"0000000000","songid":"121032821","has_mv":"0","encrypted_songid":"7407736d07509576264b9L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏","artistname":"罗文","control":"0000000000","songid":"14572354","has_mv":"0","encrypted_songid":"4906de5b4209561d19b6L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏","artistname":"押尾コータロー","control":"0000000000","songid":"52949213","has_mv":"0","encrypted_songid":"5307327f0dd09561cf027L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏放牛","artistname":"毛宁","control":"0000000000","songid":"23275598","has_mv":"0","encrypted_songid":"0507163284e09571f0d79L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"1","songname":"黄昏(邵夷贝、李志)","artistname":"邵夷贝","control":"0100000000","songid":"73976995","has_mv":"0","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"黄昏","artistname":"潘美辰","control":"0000000000","songid":"14470445","has_mv":"0","encrypted_songid":"1106dccd2d09561d056eL"}]
     * album : [{"albumname":"黄昏里的老男孩","artistpic":"http://qukufile2.qianqian.com/data2/pic/89035284/89035284.jpg","albumid":"12757314","artistname":"刘明辉"},{"albumname":"暗黒能楽集・心綺楼","artistpic":"http://c.hiphotos.baidu.com/ting/pic/item/5d6034a85edf8db1f577174d0b23dd54564e74b9.jpg","albumid":"91479362","artistname":"黄昏フロンティア"},{"albumname":"黄昏双镖客","artistpic":"http://qukufile2.qianqian.com/data2/pic/40213477/40213477.jpg","albumid":"15780189","artistname":"埃尼奥 莫里康内"},{"albumname":"魔理沙と6つのキノコ original soundtrack","artistpic":"","albumid":"23869045","artistname":"黄昏フロンティア"},{"albumname":"黄昏思情","artistpic":"http://qukufile2.qianqian.com/data2/pic/40334877/40334877.jpg","albumid":"2019169","artistname":"阿吉仔"},{"albumname":"黄昏的那份情","artistpic":"http://qukufile2.qianqian.com/data2/pic/101025355/101025355.jpg","albumid":"69972202","artistname":"陈惠弟"},{"albumname":"黄昏渡","artistpic":"http://qukufile2.qianqian.com/data2/pic/b611c69078ced3a8f63b66aeaa4a8d3b/261438472/261438472.jpg","albumid":"261438688","artistname":"侯梦亮"},{"albumname":"黄昏","artistpic":"http://qukufile2.qianqian.com/data2/pic/101172138/101172138.jpg","albumid":"83667397","artistname":"朱昊楠"},{"albumname":"黄昏オレンジ","artistpic":"http://qukufile2.qianqian.com/data2/pic/43723482/43723482.jpg","albumid":"26749536","artistname":"サクラメリーメン"},{"albumname":"黄昏エレジ","artistpic":"http://b.hiphotos.baidu.com/ting/pic/item/a8014c086e061d958cfa9a2e79f40ad162d9ca6c.jpg","albumid":"25524922","artistname":"豚乙女"}]
     * order : artist,song,album
     * error_code : 22000
     * artist : [{"yyr_artist":"0","artistid":"209711202","artistpic":"http://b.hiphotos.baidu.com/ting/abpic/item/a71ea8d3fd1f413494979b4e231f95cad1c85e59.jpg","artistname":"黄昏フロンティア"}]
     */

    private String order;
    private int error_code;
    /**
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * yyr_artist : 0
     * songname : 黄昏
     * artistname : 周传雄
     * control : 0000000000
     * songid : 2124144
     * has_mv : 1
     * encrypted_songid : 00062069700956a1c461L
     */

    private List<SongBean> song;
    /**
     * albumname : 黄昏里的老男孩
     * artistpic : http://qukufile2.qianqian.com/data2/pic/89035284/89035284.jpg
     * albumid : 12757314
     * artistname : 刘明辉
     */

    private List<AlbumBean> album;
    /**
     * yyr_artist : 0
     * artistid : 209711202
     * artistpic : http://b.hiphotos.baidu.com/ting/abpic/item/a71ea8d3fd1f413494979b4e231f95cad1c85e59.jpg
     * artistname : 黄昏フロンティア
     */

    private List<ArtistBean> artist;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongBean> getSong() {
        return song;
    }

    public void setSong(List<SongBean> song) {
        this.song = song;
    }

    public List<AlbumBean> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumBean> album) {
        this.album = album;
    }

    public List<ArtistBean> getArtist() {
        return artist;
    }

    public void setArtist(List<ArtistBean> artist) {
        this.artist = artist;
    }

    public static class SongBean {
        private String bitrate_fee;
        private String yyr_artist;
        private String songname;
        private String artistname;
        private String control;
        private String songid;
        private String has_mv;
        private String encrypted_songid;

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(String has_mv) {
            this.has_mv = has_mv;
        }

        public String getEncrypted_songid() {
            return encrypted_songid;
        }

        public void setEncrypted_songid(String encrypted_songid) {
            this.encrypted_songid = encrypted_songid;
        }
    }

    public static class AlbumBean {
        private String albumname;
        private String artistpic;
        private String albumid;
        private String artistname;

        public String getAlbumname() {
            return albumname;
        }

        public void setAlbumname(String albumname) {
            this.albumname = albumname;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getAlbumid() {
            return albumid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }
    }

    public static class ArtistBean {
        private String yyr_artist;
        private String artistid;
        private String artistpic;
        private String artistname;

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getArtistid() {
            return artistid;
        }

        public void setArtistid(String artistid) {
            this.artistid = artistid;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }
    }
}
