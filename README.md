# FeloneHelper
工作中总结的工具
###1.自定义对话框（CustomDialog）
	复制util中CustomDialog代码，
	创建布局文件
	将布局通过打气筒装到对话框
		View customView = LayoutInflater.from(this).inflate(R.layout.dialog_pay, null);
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        customDialog = builder.cancelTouchout(true)
                                           .cancelTouchout(true)
                                           .view(customView)
                                           .widthpx(ViewGroup.LayoutParams.MATCH_PARENT)
                                           .heightpx(ViewGroup.LayoutParams.WRAP_CONTENT)
                                           .style(R.style.AlertDialogStyle)
                                           .addViewOnclick(R.id.tv_cancel,
                                                           new View.OnClickListener() {
                                                               @Override
                                                               public void onClick(View v) {
                                                                   customDialog.dismiss();
                                                               }
                                                           })
                                           .addViewOnclick(R.id.tv_ok, new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   finish();
                                                   customDialog.dismiss();
                                               }
                                           })
                                           .build();
        customDialog.show();
	
<p align='center'>
<img src='images/customdialog.png' title='images' style='max-width:600px'></img>
</p>



###2.圆角textview（RoundTextView）、RoundLinearLayout等
	这个的使用需要在清单文件中application下加配置
	        <!-- 全面屏 -->
        <meta-data
            android:name="android.max_aspect"
            android:value="2.4" />
        <meta-data
            android:name="design_width"
            android:value="750" />
        <meta-data
            android:name="design_height"
            android:value="1334" />
	
