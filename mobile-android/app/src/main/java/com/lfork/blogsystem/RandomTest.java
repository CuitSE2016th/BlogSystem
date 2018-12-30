package com.lfork.blogsystem;

import java.util.Random;

/**
 * Created by 98620 on 2018/11/28.
 */
public class RandomTest {
//     public AlwaysMarqueeTextView(Context context) {
//          super(context);
//     }
//
//     public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
//          super(context, attrs);
//     }
//
//     public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
//          super(context, attrs, defStyle);
//     }

    public static String[] imagesUrl = {
            "http://pic28.photophoto.cn/20130818/0020033143720852_b.jpg",
            "https://img.zcool.cn/community/01f9ea56e282836ac72531cbe0233b.jpg@1280w_1l_2o_100sh.jpg",
            "http://www.pptbz.com/upfile/pptpic/201204/2012041411424739.jpg",
            "http://www.pptbz.com/upfile/pptpic/201412/2014121910582446.jpg",
            "http://www.pptbz.com/upfile/pptpic/201501/2015011911014015.jpg",

            "http://www.pptbz.com/upfile/pptpic/201304/2013040722515924.jpg",
            "http://www.pptbz.com/upfile/pptpic/201105/2011051707490235.jpg",
            "http://www.pptbz.com/upfile/pptpic/201110/20111020112515401.jpg",
            "http://www.pptbz.com/upfile/pptpic/201501/2015012617293543.jpg",
            "http://www.pptbz.com/upfile/pptpic/201401/2014012906432159.jpg"

    };


    public static String[] randomNames = {
            "IT",
            "Education",
            "Medical",
            "Car",
            "Technology",
            "Animation",
            "Emotion",
            "Movie",
            "Music",
            "Art",
            "Science"
            , "Computer",
            "Car "
            , "Phone",
            "Agriculture",
            "tourism",
            "politics",
            "war"
    };

    public static String[] randomTitles = {
            "The Chinese are ashamed to say something about their teeth for decades, and they have been ruthlessly exposed by Korean films."
            ,"It's so simple to make poached eggs, learn this little trick, and make sure that they are round and tender."
            ,"It's so simple to make poached eggs, learn this little trick, and make sure that they are round and tender."
            ,"The biggest joke of the domestic film: 1.5 billion investment in 30 big stars, 2.8 points, investors fleeing and fleeing"
            ,"She was considered by the Chinese to be the \"crazy\" of Cui Yongyuan, but she was named \"the world's top 100 thinkers\" by the American magazine."
            ,"Wo Yanzu's wife is ridiculed by the group: 39-year-old wrinkles, but let the male god be a 14-year-old wife slave!"
            ,"There are 5 programming sites for rookie programmers, one of which has been improved over 15 years!"
            , "[Wang Xingguo Nutrition Special Training Class] No. 5, Lesson 22, Group 5 Yingxia"
            , "If it wasn't you, then forget it."
            ,"How do programmers find the resources they want? (Novice Xiaobai)"
            ," For the future of blockchain, our imagination is not enough!!"
            ,"We need to be imaginative about the future."
            , "Google's imagination"
            , "He is the real Iron Man - Silicon Valley Iron Man reading sharing"
            , "Add Two Numbers"
            , "Several key ways to improve workplace efficiency"
            , "3 o'clock sleepless blockchain six days dry goods mix - Shuai Chu, Cai Wensheng, Li Xiao"
            , "Dr. Zhang Ming, Dr. Zhang Ming shared the blockchain from the technical value added to the social value enhancement, will be"
            ,"Reading in the South VS Reading in the North, the difference is too big."
            , "Hand Book drill, look like a mirror"
    };

    public static String getRandomNames() {
        int randomNumber = Math.abs(new Random().nextInt() % randomNames.length);
        return randomNames[randomNumber];

    }

    public static String getRandomImages() {
        int randomNumber = Math.abs(new Random().nextInt() % imagesUrl.length);
        return imagesUrl[randomNumber];
    }

    public static String getRandomTitles() {
        int randomNumber = Math.abs(new Random().nextInt() % randomTitles.length);
        return randomTitles[randomNumber];
    }


    public static String getRandomText() {
        int randomNumber = Math.abs(new Random().nextInt() % randomTitles.length);
        return randomTitles[randomNumber];
    }





}

