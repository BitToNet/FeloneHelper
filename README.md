# FeloneHelper
下面都是工作中总结的工具，全部集合在utils里面，一般都在代码中做了使用示例，直接把代码跑一遍，然后按下面的流程复制到自己项目就可以用。项目用的是AndroidStudio 3.1.3 ，高于这个版本的应该都可以正常跑起来。有目录，也可以用Ctrl+f搜索想要的内容。
# 目录：

[1. 自定义对话框（CustomDialog）](##1.自定义对话框（CustomDialog）) 

- [自定义对话框(CustomDialog)](#自定义对话框)
- [图表(Chart)](#图表(Chart)) 
- [抽屉菜单55](#抽屉菜单) 

## 自定义对话框
(CustomDialog) 



###### 复制util/CustomDialog代码，代码中有例子
###### 创建布局文件
###### 将布局通过打气筒装到对话框
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

## 2.圆角textview（RoundTextView）、RoundLinearLayout等

上面的对话框就套的一个RoundLinearLayout，把代码复制进去就Ok了，注意把资源文件也拷过去（res/anim、res/anim等）
	
###### 这个里面用到了
	// 屏幕适配
	api 'com.zhy:autolayout:1.4.5'
###### 注意：这个的使用需要在清单文件中application下加配置
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
	
## 3.仿京东选择器（日期选择，一级选择，二级选择，三级选择）

用github上面的项目整合了一个工具类，照着下面的步骤一步一步去项目中拷，哪报错补哪
###### Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
###### Add the dependency
	dependencies {
    	implementation 'com.github.shichunlei:JDAddressSelector:0.0.3'
		// AndroidPicker
    	implementation('cn.qqtheme.framework:WheelPicker:1.5.6') {
        exclude group: 'com.android.support'
    	}
	}
###### 拷贝工具类
	utils/StringUtils、PickerUtils、TimeUtils
	widegt/FloatPicker

###### 拷贝资源
	values/styles、array



## 抽屉菜单 

## 图表(Chart) 
