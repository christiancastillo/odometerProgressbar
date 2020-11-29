# odometerProgressbar


[![Everything Is AWESOME](https://raw.githubusercontent.com/mohamadsajjad/odometerProgressbar/master/demo.JPG)](https://youtu.be/1R83BnxDs60 "Everything Is AWESOME")



# Setup

### Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


### Step 2. Add the dependency

```gradle
dependencies {
	        implementation 'com.github.mohamadsajjad:odometerProgressbar:${lastestRelease}'
	}
```

# Use

1. add view to your xml layout

```xml
<com.mohamadsajjad.progressbarlibrary.CustomProgress
            android:layout_width="250dp"
            android:layout_height="250dp"
            android:id="@+id/speed_meter"/>
```
2. intialize it
```java
CustomProgress customProgress = findViewById(R.id.speed_meter);
        customProgress
                .setPercent(83)
                .setTitle("Title")
                .setDescription("Description of this progress")
                .start();//start anim
```

# License

    Copyright 2020 mohamadsajjad

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
