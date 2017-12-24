//
// Created by TJH on 2017/12/8.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_tjh_myapplication_engin_JNI_getAppKey(JNIEnv *env,jobject instance){
    std::string appkey = "18cc7d2ce569265b327de3c7c288aa79";

    return env->NewStringUTF(appkey.c_str());
}