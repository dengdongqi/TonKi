# Tonki

V:1.1.6  
build.gradle(project)  中添加

```
	allprojects {  
		repositories {  
			...  
			maven { url 'https://jitpack.io' }  
		}  
	}  
```


build.gradle(app) 中添加

```
dependencies {  
		...
	        implementation 'com.github.dengdongqi:Tonki:1.1.0'  
	}  

```


 **请先在Application类onCreate()中初始化Tonki**  
 `Tonki.init(application)`

*base*  
```
RvAdapter           		RV 模板Adapter  
TonkiActivity       		基类activity(用于对照参写具体BaseActivity)  
TonkiAdapter        		LV GV 模板Adapter  
TonkiFragment	    		基类fragment(用于对照参写具体BaseFragment)  
VpAdapter	    		Viewpager Adapter    
```

*constant*
```
TonkiConstent       		常量    
```

*helper*  
```
BottomNavigationViewHelper	取消底部导航栏item大于3时的位移动画  
DialogHelper			Dialog帮助类  
NotificationsHelper		Notifications 帮助类   
PermissionHelper		Permission  帮助类  
StatusBarManager		状态栏管理类  
TonkiDialog			支持全自定义Dialog    
```

*interfaces*
```
DialogBuilder			Dialog构造者接口  
SpeedListener			SpinView速率接口    
```

*utils*  
```
DoubleUtils           		防止双击  
KotlinUseLuban        		解决 kotlin 不能使用鲁班  
PDFUtils              		View 2 PDF  
ScalePageTransformer  		ViewPager滑动效果    
```

*view*  
```
AllShowListView       		scrollView 嵌套 LV 展示不全问题  
ClearEditText			带删除按钮的EditText
CustomDialog         		通用对话框  
ProgressView          		webView加载进度条  
ProgressWebView       		ProgressWebView  
ImageView			旋转View  
TonkiScrollView       		解决 ScrollView 内滑动冲突    
```

***依赖项：***  
    //布兰科基工具类  
    https://github.com/Blankj/AndroidUtilCode  
    implementation 'com.blankj:utilcode:1.23.6'  
    //Luban压缩  
    https://github.com/Curzibn/Luban  
    implementation 'top.zibin:Luban:1.1.8'  
    //状态栏颜色管理  
    https://github.com/msdx/status-bar-compat  
    implementation 'com.githang:status-bar-compat:0.7'  
