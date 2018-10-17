# FeloneHelper
工作中总结的工具
### 1.自定义对话框（CustomDialog）
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
![github](https://github.com/BitToNet/FeloneHelper/raw/master/img/customdialog.png)

### 2.圆角textview（RoundTextView）、RoundLinearLayout等
	上面的对话框就套的一个RoundLinearLayout，把代码复制进去就Ok了，注意把资源文件也拷过去（res/anim、res/anim等）
	
	这个里面用到了
    		// 屏幕适配
    		api 'com.zhy:autolayout:1.4.5'
	注意：这个的使用需要在清单文件中application下加配置
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
	
