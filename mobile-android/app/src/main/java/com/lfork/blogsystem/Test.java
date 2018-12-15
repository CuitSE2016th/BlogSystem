package com.lfork.blogsystem;

import java.util.Random;

/**
 * Created by 98620 on 2018/11/28.
 */
public class Test {
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

    public static String getRandomImages() {
        int randomNumber = Math.abs(new Random().nextInt() % 10);
        return imagesUrl[randomNumber];
    }


}

