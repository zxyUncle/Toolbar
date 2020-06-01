
[![](https://jitpack.io/v/zxyUncle/zxyMultilingual.svg)](https://jitpack.io/#zxyUncle/zxyMultilingual)


Gradle
-----
Step 1    
	

     allprojects {  
		repositories {    
			...    
			maven { url 'https://jitpack.io' }     
		}    
	}      
	

使用

        <com.zxy.zxytoolbar.ZxyToolbar
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:leftImg="@drawable/zxy_back"
        app:rightImg1="@drawable/zxy_share"
        app:rightImg2="@drawable/zxy_share"
        app:rightText="报名"
        app:rightTextColor="@color/color_999999"
        app:titleTextColor="@color/color_999999" />

