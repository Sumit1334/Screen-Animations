package com.sumit1334.screenanimation;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.*;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;

public class ScreenAnimation extends AndroidNonvisibleComponent {
  private Activity activity;
  private Form form;
  private boolean isCompanion;
  private View view;

  public ScreenAnimation(ComponentContainer container) {
    super(container.$form());
    this.form=container.$form();
    this.activity= container.$context();
    this.isCompanion=this.form instanceof ReplForm;
    this.view=this.activity.findViewById(16908290);
    if (this.activity.getIntent().getExtras().getString("type")!=null)
      CloseAnimation();
  }

// This function will animates and open the new screen with given technique and pass the given value as start value

  @SimpleFunction
  public void OpenScreen(String screenName,String technique,Object startValue) {
    if (!isCompanion) {
//      Checking the technique if valid then animation will be played
        if (technique.equals(this.Rotate()))
          this.StartRotate(screenName, startValue,technique);
        else if (technique.equals(this.Card()))
          this.StartCard(screenName,startValue,technique);
        else if (technique.equals(this.InAndOut()))
          this.StartInAndOut(screenName,startValue,technique);
        else if (technique.equals(this.Diagonal()))
          this.StartDiagonal(screenName,startValue,technique);
        else if (technique.equals(this.Fade()))
          this.StartFade(screenName,startValue,technique);
        else if (technique.equals(this.Shrink()))
          this.StartShrink(screenName,startValue,technique);
        else if (technique.equals(this.Spin()))
          this.StartSpin(screenName,startValue,technique);
        else if (technique.equals(this.Split()))
          this.StartSplit(screenName,startValue,technique);
        else if (technique.equals(this.Zoom()))
          this.StartZoom(screenName,startValue,technique);
        else
//        Invalid technique. No Animation will be played
          throw new YailRuntimeError("Invalid Technique given", "Invalid Technique");
    }
  }

//  This function will animate our new screen with the given technique while opening screen with the extension

  private void CloseAnimation() {
    String technique=this.activity.getIntent().getExtras().getString("type");
    if (!technique.isEmpty()) {
//      Checking the technique if valid then animation will be played
        if (technique.equals(this.Rotate()))
          this.EndRotate(this.view);
        else if (technique.equals(this.Card()))
          this.EndCard(this.view);
        else if (technique.equals(this.InAndOut()))
          this.EndInAndOut(this.view);
        else if (technique.equals(this.Diagonal()))
          this.EndDiagonal(this.view);
        else if (technique.equals(this.Fade()))
          this.EndFade(this.view);
        else if (technique.equals(this.Shrink()))
          this.EndShrink(this.view);
        else if (technique.equals(this.Spin()))
          this.EndSpin(this.view);
        else if (technique.equals(this.Split()))
          this.EndSplit(this.view);
        else if (technique.equals(this.Zoom()))
          this.EndZoom(this.view);
        else
//        Invalid technique. No Animation will be played
          throw new YailRuntimeError("Invalid Technique given", "Invalid Technique");
    }
  }

//  Returns the start value that you have given while opening screen with this extension

  @SimpleFunction
  public String GetStartValue(){
    String value=this.activity.getIntent().getExtras().getString("value");
    if (value!=null)
      return value;
    else
//    Returning an empty string if the start value is null
//    If we returned null then it will cause a error
      return "";
  }

//  All types of animation properties are here
//  There are total 9 types of animation or properties

  @SimpleProperty
  public String Rotate(){
    return "Rotate";
  }
  @SimpleProperty
  public String Card(){
    return "Card";
  }
  @SimpleProperty
  public String Diagonal(){
    return "Diagonal";
  }
  @SimpleProperty
  public String InAndOut(){
    return "InAndOut";
  }
  @SimpleProperty
  public String Fade(){
    return "Fade";
  }
  @SimpleProperty
  public String Shrink(){
    return "Shrink";
  }
  @SimpleProperty
  public String Spin(){
    return "Spin";
  }
  @SimpleProperty
  public String Split(){
    return "Split";
  }
  @SimpleProperty
  public String Zoom(){
    return "Zoom";
  }

//  Invoking and making the animation programitically

  private void StartRotate(String name,Object startValue,String technique){
    RotateAnimation rotateAnimation=new RotateAnimation(0,-180,50,50);
    ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,0,1.0f,0,50,50);
    AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0);
    AnimationSet animation=new AnimationSet(true);
    animation.addAnimation(rotateAnimation);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setInterpolator(new DecelerateInterpolator());
    animation.setDuration(400);
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndRotate(View view){
    RotateAnimation rotateAnimation=new RotateAnimation(180,0,50,50);
    ScaleAnimation scaleAnimation=new ScaleAnimation(0,1.0f,0,1.0f,50,50);
    AlphaAnimation alphaAnimation=new AlphaAnimation(0,1.0f);
    AnimationSet animation=new AnimationSet(true);
    animation.addAnimation(rotateAnimation);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setInterpolator(new DecelerateInterpolator());
    animation.setDuration(400);
    view.startAnimation(animation);
  }
  private void StartCard(String name,Object startValue,String technique){
    ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,0.5f,1.0f,0.5f,this.px2dp(this.form.Height()/2),this.px2dp(this.form.Width()/2));
    AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.5f);
    AnimationSet animation=new AnimationSet(true);
    animation.setInterpolator(new AccelerateDecelerateInterpolator());
    animation.setDuration(500);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndCard(View view){
    TranslateAnimation animation = new TranslateAnimation(-1000,0,0,0);
    animation.setDuration(500);
    animation.setInterpolator(new AccelerateDecelerateInterpolator());
    view.startAnimation(animation);
  }
  private void StartDiagonal(String name,Object startValue,String technique){
    TranslateAnimation translateAnimation=new TranslateAnimation(0,(this.px2dp(this.form.Height())),0,(this.px2dp(this.form.Width())));
    ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,0.0f,1.0f,0.0f);
    AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
    AnimationSet animation=new AnimationSet(true);
    animation.addAnimation(translateAnimation);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setDuration(500);
    animation.setInterpolator(new DecelerateInterpolator());
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndDiagonal(View view){
    TranslateAnimation translateAnimation=new TranslateAnimation(-(this.px2dp(this.form.Height())),0,-(this.px2dp(this.form.Height())),0);
    ScaleAnimation scaleAnimation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f);
    AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
    AnimationSet animation=new AnimationSet(true);
    animation.addAnimation(translateAnimation);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setDuration(500);
    animation.setInterpolator(new DecelerateInterpolator());
    view.startAnimation(animation);
  }
  private void StartInAndOut(String name,Object startValue,String technique){
    TranslateAnimation translateAnimation=new TranslateAnimation(0,-1000,0,0);
    ScaleAnimation scaleAnimation=new ScaleAnimation(1.5f,0.5f,1.0f,0.5f,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.5f);
    AnimationSet animation=new AnimationSet(true);
    animation.addAnimation(translateAnimation);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setDuration(500);
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndInAndOut(View view){
    TranslateAnimation translateAnimation=new TranslateAnimation(1000,0,0,0);
    ScaleAnimation scaleAnimation=new ScaleAnimation(0.5f,1.0f,0.5f,1.0f,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    AlphaAnimation alphaAnimation=new AlphaAnimation(0.5f,1.0f);
    AnimationSet animation=new AnimationSet(true);
    animation.addAnimation(translateAnimation);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setDuration(500);
    view.startAnimation(animation);
  }
  private void StartFade(String name,Object startValue,String technique){
    AlphaAnimation animation=new AlphaAnimation(1.0f,0.0f);
    animation.setInterpolator(new AccelerateInterpolator());
    animation.setDuration(500);
    animation.setFillAfter(false);
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndFade(View view){
    AlphaAnimation animation=new AlphaAnimation(0.0f,1.0f);
    animation.setInterpolator(new AccelerateInterpolator());
    animation.setDuration(500);
    animation.setFillAfter(false);
    view.startAnimation(animation);
  }
  private void StartShrink(String name,Object startValue,String technique){
    ScaleAnimation animation=new ScaleAnimation(1.0f,0.0f,1.f,0.0f,this.px2dp(this.form.Height()/2),this.px2dp(this.form.Width()/2));
    animation.setDuration(500);
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndShrink(View view){
    ScaleAnimation animation=new ScaleAnimation(0.0f,1.0f,0.f,1.0f,this.px2dp(this.form.Height()/2),this.px2dp(this.form.Width()/2));
    animation.setDuration(500);
    view.startAnimation(animation);
  }
  private void StartSpin(String name,Object startValue,String technique){
    RotateAnimation rotateAnimation=new RotateAnimation(0,720,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,0.0f,1.0f,0.0f,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
    AnimationSet animation=new AnimationSet(true);
    animation.setInterpolator(new DecelerateInterpolator());
    animation.addAnimation(rotateAnimation);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setDuration(500);
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndSpin(View view){
    ScaleAnimation scaleAnimation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    RotateAnimation rotateAnimation=new RotateAnimation(720,0,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
    AnimationSet animation=new AnimationSet(true);
    animation.setInterpolator(new DecelerateInterpolator());
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(rotateAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setDuration(500);
    view.startAnimation(animation);
  }
  private void StartSplit(String name,Object startValue,String technique){
    ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,0.0f,1.0f,1.0f,(this.px2dp(this.form.Height()/2)),0);
    scaleAnimation.setDuration(500);
    scaleAnimation.setFillAfter(false);
    final Class screen=this.getClass(name);
    this.view.startAnimation(scaleAnimation);
    this.Listen(scaleAnimation,screen,startValue.toString(),technique);
  }
  private void EndSplit(View view){
    ScaleAnimation scaleAnimation=new ScaleAnimation(0.0f,1.0f,1.0f,1.0f,(this.px2dp(this.form.Height()/2)),0);
    scaleAnimation.setDuration(500);
    scaleAnimation.setFillAfter(true);
    view.startAnimation(scaleAnimation);
  }
  private void StartZoom(String name,Object startValue,String technique){
    ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f,0.5f,1.0f,0.5f,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
    AnimationSet animation=new AnimationSet(true);
    animation.addAnimation(scaleAnimation);
    animation.addAnimation(alphaAnimation);
    animation.setInterpolator(new DecelerateInterpolator());
    animation.setDuration(500);
    animation.setZAdjustment(1);
    final Class screen=this.getClass(name);
    this.view.startAnimation(animation);
    this.Listen(animation,screen,startValue.toString(),technique);
  }
  private void EndZoom(View view){
    ScaleAnimation animation=new ScaleAnimation(2.0f,1.0f,2.0f,1.0f,(this.px2dp(this.form.Height()/2)),(this.px2dp(this.form.Width()/2)));
    animation.setDuration(500);
    animation.setInterpolator(new DecelerateInterpolator());
    view.startAnimation(animation);
    }

//  Listener for all animation is coded here

  private void Listen(Animation animation,Class screen,String value,String technique){
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {}

      @Override
      public void onAnimationEnd(Animation animation) {
        Intent intent=new Intent(form,screen);
        intent.putExtra("value",value);
        intent.putExtra("type",technique);
        form.OpenScreenAnimation("none");
        form.CloseScreenAnimation("none");
        activity.startActivity(intent);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
  }

//  Getting the class name for opening the given screen
//  Thanks Mohamed Tamer for helping me in this code

  private Class getClass(String screenName){
    try {
      return Class.forName(form.getPackageName()+"."+screenName);
    } catch (ClassNotFoundException e) {
      throw new YailRuntimeError(screenName+ "not found","Screen not found");
    }
  }

//  Converting pixel to dp
//  This will help us to convert the screen width and height to dp from pixels

  private int px2dp(int px){
    return (int) (this.activity.getResources().getDisplayMetrics().density*px);
  }
}
