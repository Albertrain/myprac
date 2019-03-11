package com.train.amm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * #常见布局
 * ###相对布局
 * #####RelativeLayout
 * * 组件默认左对齐、顶部对齐
 * * 设置组件在指定组件的右边
 *
 * 		 android:layout_toRightOf="@id/tv1"
 * * 设置在指定组件的下边
 *
 * 		android:layout_below="@id/tv1"
 * * 设置右对齐父元素
 *
 * 		android:layout_alignParentRight="true"
 * * 设置与指定组件右对齐
 *
 * 		 android:layout_alignRight="@id/tv1"
 *
 * ###线性布局
 * #####LinearLayout
 * * 指定各个节点的排列方向
 *
 * 		android:orientation="horizontal"
 * * 设置右对齐
 *
 * 		android:layout_gravity="right"
 * * 当竖直布局时，只能左右对齐和水平居中，顶部底部对齐竖直居中无效
 * * 当水平布局时，只能顶部底部对齐和竖直居中
 * * 使用match_parent时注意不要把其他组件顶出去
 * * 线性布局非常重要的一个属性：权重
 *
 * 		android:layout_weight="1"
 * * 权重设置的是按比例分配剩余的空间
 *
 * ###帧布局
 * #####FrameLayout
 * * 默认组件都是左对齐和顶部对齐，每个组件相当于一个div
 * * 可以更改对齐方式
 *
 * 		android:layout_gravity="bottom"
 * * 不能相对于其他组件布局
 *
 * ###表格布局
 * #####TableLayout
 * * 每个<TableRow/>节点是一行，它的每个子节点是一列
 * * 表格布局中的节点可以不设置宽高，因为设置了也无效
 * 	* 根节点<TableLayout/>的子节点宽为匹配父元素，高为包裹内容
 * 	* <TableRow/>节点的子节点宽为包裹内容，高为包裹内容
 * 	* 以上默认属性无法修改
 *
 * * 根节点中可以设置以下属性，表示让第1列拉伸填满屏幕宽度的剩余空间
 *
 * 		android:stretchColumns="1"
 *
 * ###绝对布局
 * #####AbsoluteLayout
 * * 直接指定组件的x、y坐标
 *
 * 		android:layout_x="144dp"
 *         android:layout_y="154dp"
 */
public class LayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        //相对布局实战
    }
}
