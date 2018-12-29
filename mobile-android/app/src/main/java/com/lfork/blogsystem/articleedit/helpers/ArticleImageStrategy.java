//package com.lfork.blogsystem.articleedit.helpers;
//
//import android.app.ProgressDialog;
//import android.net.Uri;
//import android.os.AsyncTask;
//
//import com.chinalwb.are.spans.AreImageSpan;
//import com.chinalwb.are.strategies.ImageStrategy;
//import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Image;
//import com.lfork.blogsystem.BlogApplication;
//import com.lfork.blogsystem.base.network.Result;
//import com.lfork.blogsystem.data.article.ArticleDataRepository;
//
//import java.io.File;
//import java.lang.ref.WeakReference;
//import java.util.Objects;
//
//import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;
//
//public class ArticleImageStrategy implements ImageStrategy {
//    @Override
//    public void uploadAndInsertImage(Uri uri, ARE_Style_Image areStyleImage) {
//        new UploadImageTask(areStyleImage).executeOnExecutor(THREAD_POOL_EXECUTOR, uri);
//    }
//
//    private static class UploadImageTask extends AsyncTask<Uri, Integer, String> {
//
//        WeakReference<ARE_Style_Image> areStyleImage;
//        private ProgressDialog dialog;
//
//        UploadImageTask(ARE_Style_Image styleImage) {
//            this.areStyleImage = new WeakReference<>(styleImage);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if (dialog == null) {
//                dialog = ProgressDialog.show(
//                        areStyleImage.get().getEditText().getContext(),
//                        "",
//                        "Uploading image. Please wait...",
//                        true);
//            } else {
//                dialog.show();
//            }
//        }
//
//        @Override
//        protected String doInBackground(Uri... uris) {
//            if (uris != null && uris.length > 0) {
////                try {
//                    Result<String> result = ArticleDataRepository.INSTANCE.uploadArticleImages(Objects.requireNonNull(BlogApplication.getToken()), new File(uris[0].getPath()));
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//
//                // Returns the image url on server here
//
//                if (result != null) {
//                    return  result.getData();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            if (dialog != null) {
//                dialog.dismiss();
//            }
//            if (areStyleImage.get() != null) {
//                areStyleImage.get().insertImage(s, AreImageSpan.ImageType.URL);
//            }
//        }
//    }
//}
