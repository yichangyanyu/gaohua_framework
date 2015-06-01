package com.bm.gaohua_framework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.bm.gaohua_framework.R;
import com.bm.gaohua_framework.interfaces.IBaseActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * 带展开效果的TEXTVIEW
 */
public class ExtendsTextViewActivity extends Activity implements IBaseActivity {

    private ExpandableTextView expTv1;
    private ExpandableTextView expTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_extends);
        initView();
        initData();

    }


    @Override
    public void close(View v) {
        this.finish();
    }

    @Override
    public void initView() {
        expTv1 = (ExpandableTextView) this.findViewById(R.id.expand_text_view);
        expTv2 = (ExpandableTextView) this.findViewById(R.id.expand_text_view2);
    }

    @Override
    public void initData() {
        expTv1.setText("推开窗，一缕淡淡的清香扑鼻而来，五月，槐花飘香的季节，这样的季节我是喜欢的，喜欢槐花素素的白，一串串压在枝头，不沾一丝尘埃。喜欢这淡淡的味道，在淡淡的味道里慢慢沉淀记忆，让得与失，喜与忧，爱与恨，淡淡的成为藏在心底深处的一道风景，细细地品味，都成了一笑而过的淡然。\n" +
                "\n" +
                "轻轻握住一缕阳光，便有了阳光的心态，感恩生活给予我们一切，不抱怨，不纠缠，不颓废，享受平常的温暖，知足达观，真实坦然。\n" +
                "\n" +
                "安静地生活，过着平常的日子，守侯淡泊的岁月，即使是与亲人一起吃顿饭，看着自己做的饭菜被亲人吃的津津有味，那也是一种幸福。生活不就是这琐碎和温暖么，要求的很多，得到的很少，是一种失落。而要求的不多，得到的不少，却是一种幸福。我们不必强求什么，心简单了，世界就会变得简单。人的欲望越大，心就越复杂，烦恼就会越多。繁华的尘世烟火里，我们做好自己，记着别人的好，做一个安静的人，在纷繁复杂中感恩地生活，就是一种幸福。\n" +
                "\n" +
                "而宁静，于我们似乎变得有点奢侈，宁静，是一种典雅的气质，一种古朴的情怀。向往\"曲径通幽处，禅房花木深”的宁静深远，想着于一个清幽的小院，在一颗开花的树下，品一杯香茗，读自己喜欢的书，抒几句深情的感悟，倾听花开的声音。此刻，时间归于寂静，生命归于平淡。\n" +
                "\n" +
                "一朵花开，从时光的缝隙里纵穿过来，在我们还未察觉时，已将我们开得寂寞的青春摇曳至落红成泥，几乎忘了时间的远去，只以为是红颜弹指老，就会把不同的结果与命运决然呈现。一片叶绿，恍惚间就蔓延成海，时光远去，青春不再，一拐弯就看不见踪影，一种顽强的力量却在泥土深处潜伏舒展，寻找生命永恒的根基，等待下一季轮回。");
        expTv2.setText("推开窗，一缕淡淡的清香扑鼻而来，五月，槐花飘香的季节，这样的季节我是喜欢的，喜欢槐花素素的白，一串串压在枝头，不沾一丝尘埃。喜欢这淡淡的味道，在淡淡的味道里慢慢沉淀记忆，让得与失，喜与忧，爱与恨，淡淡的成为藏在心底深处的一道风景，细细地品味，都成了一笑而过的淡然。\n" +
                "\n" +
                "“我不怕被辜负，因为我更喜欢活着的感觉”。活着真好，可以听到清晨鸟儿的第一声清脆的鸣叫，可以看到孩子那双不然纤尘纯真的眼睛，可以爱，可以期待，我们还有未来。活着，就不要辜负光阴馈赠与我们的美好！\n" +
                "\n" +
                "生命的颜色，如或深或浅的叶绿，自然、简单却又热烈。而生命的美，不在它的绚烂，而在它的平和。熙熙攘攘的尘世里，我们每天都在忙碌的奔波着，色彩斑斓的世界常常让我们忘了停下脚步，去认真审视生命的深度。\n" +
                "\n" +
                "而宁静，于我们似乎变得有点奢侈，宁静，是一种典雅的气质，一种古朴的情怀。向往\"曲径通幽处，禅房花木深”的宁静深远，想着于一个清幽的小院，在一颗开花的树下，品一杯香茗，读自己喜欢的书，抒几句深情的感悟，倾听花开的声音。此刻，时间归于寂静，生命归于平淡。\n" +
                "\n" +
                "一朵花开，从时光的缝隙里纵穿过来，在我们还未察觉时，已将我们开得寂寞的青春摇曳至落红成泥，几乎忘了时间的远去，只以为是红颜弹指老，就会把不同的结果与命运决然呈现。一片叶绿，恍惚间就蔓延成海，时光远去，青春不再，一拐弯就看不见踪影，一种顽强的力量却在泥土深处潜伏舒展，寻找生命永恒的根基，等待下一季轮回。");
    }
}
