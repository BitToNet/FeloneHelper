# FeloneHelper
## 一切都是为了更好地复制粘贴   
下面都是工作中总结的工具，全部集合在utils里面，一般都在代码中做了使用示例，直接把代码跑一遍，然后按下面的流程复制到自己项目就可以用。项目用的是AndroidStudio 3.1.3 ，高于这个版本的应该都可以正常跑起来。有目录，也可以用Ctrl+f搜索想要的内容。   
项目没有用butterknife、lambda，没有基类（因为他们的一些特性会导致不方便复制粘贴）
# 目录：
富文本
通讯录（准备做）
- [5.RecycleView帮助类](#多功能列表适配器)
- [4.照片选择器（多张）](#照片选择器)
- [3.仿京东选择器（日期选择，一级选择，二级选择，三级选择）](#仿京东选择器)
- [2.圆角控件RoundTextView、RoundLinearLayout等](#各种圆角控件)
- [1.自定义对话框(CustomDialog)](#自定义对话框)

## 多功能列表适配器
5.RecycleView帮助类   
功能非常强大的帮助类 ，原作者地址[BRVAH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)   
这里对帮助类做了封装，加了很多功能，使得recycleview的使用变得非常方便灵活   
所有项目中都设计到了recycleview的内容都是通过他实现的，比如照片选择器，通讯录
###### Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
###### Add the dependency
	dependencies {
        api "com.android.support:recyclerview-v7:28.0.0"
		// adapter适配
    	api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.38'
		// 图片选择库,包含gradle
    	api 'com.github.LuckSiege.PictureSelector:picture_library:v2.2.3'
    	// 评分控件
    	api 'me.zhanghai.android.materialratingbar:library:1.3.1'
	}
###### 拷贝基类
	主要是这个，然后根据需要他拷其他资源
	holder/BaseViewHolderHelper

###### 几行代码就可以搞定
    private List<Bean>     mDateList;
    private LinearLayoutManager manager;
    private YouAdapter         mAdapter;
    
	manager = new LinearLayoutManager(this);
    manager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(manager);
    mAdapter = new YouAdapter(mDateList);
    mRecyclerView.setAdapter(mAdapter);
###### YouAdapter定制适配器   
		1.把k换为数据的bean类
		2.加list布局R.layout.item
		3.用helper给每个条目设置资源
	
	public class YouAdapter extends BaseQuickAdapter<K, BaseViewHolderHelper> {
    	private List<K> mData;

    	public ChooseCityAdapter(@Nullable List<K> data) {
        	super(R.layout.item, data);
        	mData = data;
    	}

    	@Override
    	protected void convert(BaseViewHolderHelper helper, K item) {
        	helper.setText(R.id.tvName, item.getName());
			helper.setImageUrl(R.id.iv_icon, item.getImageUrl(), R.drawable.);
    	}
	}
		

## 照片选择器
4.照片选择器（多张）  
![github](https://github.com/BitToNet/FeloneHelper/raw/master/img/photo1.png)
###### Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
###### Add the dependency
	dependencies {
        api "com.android.support:recyclerview-v7:28.0.0"
		// adapter适配
    	api 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.38'
		// 图片选择库,包含gradle
    	api 'com.github.LuckSiege.PictureSelector:picture_library:v2.2.3'
    	// 评分控件
    	api 'me.zhanghai.android.materialratingbar:library:1.3.1'
	}

###### 代码部分在AActivity里面
	
###### 拷贝工具类
	utils/FullyGridLayoutManager、DownloadImageTask、ViewUtils
	holder/BaseViewHolderHelper、RvHolder
	widget/SmoothCheckBox
	bean/ImageBean
	activity/PhotoViewActivity
	adapter/GridImageAdapter、PhotoViewAdapter
###### 资源报错的话，拷贝资源attrs
###### 加权限
	<!-- 读取扩展存储，从扩展卡读取数据 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
	<!-- 写入扩展存储 -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <!-- 相机 -->
    <uses-permission android:name="android.permission.CAMERA" />

## 仿京东选择器
3.仿京东选择器（日期选择，一级选择，二级选择，三级选择）   
![github](https://github.com/BitToNet/FeloneHelper/raw/master/img/selector.png)  
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

## 各种圆角控件
2.圆角RoundTextView、RoundLinearLayout等   
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

## 自定义对话框
1.(CustomDialog) [github上README.md标题不能带英文和字符，不然目录无法跳转]
![github](https://github.com/BitToNet/FeloneHelper/raw/master/img/customdialog.png)
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

