# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.sumit1334.screenanimation.ScreenAnimation {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/sumit1334/screenanimation/repack'
-flattenpackagehierarchy
-dontpreverify
