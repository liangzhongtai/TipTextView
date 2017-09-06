# abstract
该项目主要演示如何使用TipTextView角标控件库.
## TipTextView
---------------

> 关于我，欢迎关注
  博客：[简书](http://www.jianshu.com/users/8d01db870d4a/timeline) 微信：[lzt橘子](18520660170)

（项目背景/作用介绍）

#### 示例图片:

### 特性
- TipTextView提供四种模式,固定模式，(默认/居中)环绕模式，垂直居中环绕模式和水平居中环绕模式,其中固定模式和居中环绕模式支持Drawable.
  TipTextView的角标支持(左 上 右 下 左上 左下 右上 右下)八个方向,支持在xml或java代码控制角标背景颜色和半径，角标文字颜色和大小，
	         环绕间隔大小,角标形状(圆形，矩形，圆角矩形，椭圆形(5.0适用)),圆角大小,边框颜色和大小;
- TipLayout可以用于嵌套Button ImageView CheckBox,角标效果和TipTextView类似;
- TipLinearLayout 可用作具有角标功能的LinearLayout使用，注意内部子View必须使用layout_weight参数作为宽度指标,
                  否则请使用LinearLayout搭配TipTextView使用;
- TipRadioGroup	  可用作具有角标功能的RadioGroup使用，注意内部只能包含RadioButton，
                  且RadioButton必须使用layout_weight参数作为宽度指标;
方向的角标.
### 原理说明

TipTextView,主要依赖paint来绘制角标，角标的背景半径，颜色和字体大小，颜色，角标偏移量，方向都可以根据提供的自定义属性在xml或代码中
控制.
固定模式根据TipTextView整个控件的宽高来固定角标的位置,可以用tipSurroundPadding参数对偏移量做微调;
环绕模式，顾名思义，按TipTextView的字体和Drawable的宽高来计算角标的位置,可以用tipSurroundPadding参数对偏移量做微调.
![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/tiptextview_0.png)

TipLayout和TipRadioGroup的原理，功能和TipTextView类似.
TipLayout则作为外部布局，提供对Button,Imageview,CheckBox添加角标的支持，支持在xml或代码中动态添加角标.

TipLinearLayout本身可以作为LinearLayout使用，并且具备给内部的Child添加独立角标的功能,支持在代码中动态添加角标.
TipRadioGroup本身可以作为RadioGroup使用，并且具备给内部的RadioButton添加独立角标的功能,支持在代码中动态添加角标.

### 下载安装
Project_Gradle
``` xml
allprojects {
		    repositories {
			    maven { url 'https://jitpack.io' }
		    }
	}
```
App_Gradle:
``` xml
dependencies {
	        compile ''com.github.liangzhongtai:TipTextView:v1.0'
	}
```
### 使用方法

## 1.TipTextView

(1).在xml中可以直接使用自定义属性来设置部分属性
        "ttv_textcolor"---------------角标文本颜色

        "ttv_radius" -----------------角标背景半径
        "ttv_sice" -------------------角标文本字体大小
        "ttv_type" -------------------角标模式
            "fixation" ------固定模式
            "surround" ------居中环绕
            "surround_v" ----垂直环绕
            "surround_h" ----水平环绕
        "ttv_surround_padding"--------角标偏移量
        "ttv_direction"---------------角标位置
        	"left" ----------左
        	"top" -----------上
        	"right" ---------右
        	"bottom"---------下
        	"topRight"-------右上
        	"bottomRight"----右下
        	"topLeft"--------左上
        	"bottomLeft"-----左下

![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/tiptextview_6.png)

(2).在代码中也可以动态设置
设置角标文本

![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/tiptextview_1.png)

设置角标颜色,大小

![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/tiptextview_2.png)

设置角标方向,形状

![image](https://github.com/liangzhongtai/TipTextView/blob/master/resultPic/tiptextview_3.png)


## 2.TipLayout
(1).在xml中可以直接使用自定义属性来设置部分属性,同TipTextView;

![image](https://github.com/liangzhongtai/ReplaceFonts/blob/master/resultPic/tiptextview_4.png)

(2).在代码中也可以动态设置,set方法同TipTextView.

## 3.TipLinearLayout
(1).在xml中可以直接使用自定义属性来设置部分属性,此时TipLinearLayout内部的Child都使用同一设置,同TipTextView;
(2).在代码中，可以单独给指定的Child设置角标背景颜色和角标半径，角标字体颜色和角标字体大小,set方法同TipTextView.

## 4.TipRadioGroup
(1).在xml中可以直接使用自定义属性来设置部分属性,此时TipRadioGroup内部的RadioButton都使用同一设置,同TipTextView;
(2).在代码中，可以单独给指定的RadioButton设置角标背景颜色和角标半径，角标字体颜色和角标字体大小,set方法同TipTextView.

### 注意事项

-注意,在使用TipRadioGroup时,使用setTipSices(int index,float tipSice)/setTipRadiuses(int index,int tipRadius)
      方法给该控件内部的指定的RadioButton设置角标文字大小/角标背景半径时,
 请务必确保先前已调用setTipSices(int[] tipSices)/setTipRadiuses(int[] tipRadiuses)方法.

## License

