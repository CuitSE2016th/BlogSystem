import android.content.Context
import com.lfork.blogsystem.BlogApplication


fun dip2px(dpValue: Float): Int {
    val scale = BlogApplication.context!!.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}
