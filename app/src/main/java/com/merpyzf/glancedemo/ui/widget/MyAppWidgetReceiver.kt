package com.merpyzf.glancedemo.ui.widget

import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.AndroidRemoteViews
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentHeight
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontFamily
import androidx.glance.text.FontStyle
import androidx.glance.text.Text
import androidx.glance.text.TextDecoration
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.merpyzf.glancedemo.R

/**
 *@author: WangKe
 *@date: 2023/9/22 0022
 */
class MyAppWidgetReceiver() : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = MyAppWidget()


    inner class MyAppWidget : GlanceAppWidget() {
        override suspend fun provideGlance(context: Context, id: GlanceId) {
            provideContent {
                MyContent()
            }
        }

    }

    @Composable
    private fun MyContent() {
        Column(
            modifier = GlanceModifier.fillMaxSize().background(Color.White),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = GlanceModifier.defaultWeight().padding(12.dp)) {
                item {
                    Column(
                        modifier = GlanceModifier.fillMaxWidth().cornerRadius(6.dp).padding(6.dp)
                            .clickable {
                                Log.i("wk", "内容被点击了")
                            }) {
                        TextSupport(
                            text = """
                        有时听闻别人说，想念家里某某从前烧的什么菜，但人没了，菜也一起没了，就心生警惕。我的经验是，若有什么一生持续念想的菜色，赶得及，就应该设法学会。以后长路走远，恐怕前后无人，把一道家常菜反复练熟，随身携带，是自保的手段。逝者唤不回，如果连菜也丢了，味觉以后就再也无处可泊岸。
                    """.trimIndent()
                        )
                        Image(
                            provider = ImageProvider(R.drawable.image),
                            modifier = GlanceModifier.cornerRadius(10.dp)
                                .width(100.dp)
                                .height(149.dp),
                            contentScale = ContentScale.Crop,
                            contentDescription = "电影封面"
                        )
                    }
                }
            }
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = GlanceModifier.defaultWeight().padding(6.dp).cornerRadius(4.dp)
                        .clickable {
                            Log.i("wk", "书籍信息被点击了")
                        }) {
                    Image(
                        provider = ImageProvider(R.drawable.image),
                        contentDescription = "书籍封面",
                        modifier = GlanceModifier.width(30.dp).height(40.dp).cornerRadius(4.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = GlanceModifier.width(6.dp))
                    Column {
                        Text(text = "老派少女购物路线")
                        Text(text = "洪爱珠")
                    }
                }
                Spacer(modifier = GlanceModifier.width(12.dp))
                Row {
                    Image(
                        provider = ImageProvider(R.drawable.round_refresh_24),
                        contentDescription = "刷新",
                        modifier = GlanceModifier.cornerRadius(2.dp).clickable {
                            Log.i("wk", "刷新")
                        }
                    )
                    Spacer(modifier = GlanceModifier.width(12.dp))
                    Image(
                        provider = ImageProvider(R.drawable.round_add_24),
                        contentDescription = "添加",
                        modifier = GlanceModifier.clickable {
                            Log.i("wk", "添加")
                        }
                    )
                    Spacer(modifier = GlanceModifier.width(12.dp))
                    Image(
                        provider = ImageProvider(R.drawable.baseline_more_vert_24),
                        contentDescription = "更多",
                        modifier = GlanceModifier.clickable {
                            Log.i("wk", "更多")
                        }
                    )
                    Spacer(modifier = GlanceModifier.width(12.dp))
                }


            }
        }

    }

    @Composable
    fun TextSupport(text: String) {
        val remoteView = RemoteViews(
            LocalContext.current.packageName,
            R.layout.remote_text_layout
        )
        AndroidRemoteViews(
            remoteViews = remoteView, modifier = GlanceModifier.wrapContentSize(),
            containerViewId = R.id.textView
        ) {
            remoteView.setTextViewText(R.id.textView, text)
        }
    }

}