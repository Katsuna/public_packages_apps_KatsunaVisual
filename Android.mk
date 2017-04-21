LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_RESOURCE_DIR := \
    $(LOCAL_PATH)/res \
    frameworks/support/v7/appcompat/res \
    frameworks/support/v7/recyclerview/res \
    frameworks/support/v7/cardview/res \
    frameworks/support/design/res

LOCAL_STATIC_JAVA_LIBRARIES := \
    android-support-v4 \
    android-support-v7-appcompat \
    android-support-v7-recyclerview \
    android-support-v7-cardview \
    android-support-design \

LOCAL_STATIC_JAVA_AAR_LIBRARIES := playbase920
LOCAL_STATIC_JAVA_AAR_LIBRARIES += playbasement920
LOCAL_STATIC_JAVA_AAR_LIBRARIES += playappindexing920

LOCAL_AAPT_INCLUDE_ALL_RESOURCES := true
LOCAL_AAPT_FLAGS := --auto-add-overlay
LOCAL_AAPT_FLAGS += --generate-dependencies
LOCAL_AAPT_FLAGS += --extra-packages android.support.v7.appcompat
LOCAL_AAPT_FLAGS += --extra-packages android.support.v7.recyclerview
LOCAL_AAPT_FLAGS += --extra-packages android.support.v7.cardview
LOCAL_AAPT_FLAGS += --extra-packages android.support.design
LOCAL_AAPT_FLAGS += --extra-packages com.google.android.gms
LOCAL_AAPT_FLAGS += --extra-packages com.google.android.gms.base
LOCAL_AAPT_FLAGS += --extra-packages com.google.android.gms.appindexing

LOCAL_PACKAGE_NAME := KatsunaVisual

#LOCAL_PROGUARD_FLAG_FILES := app/proguard-rules.pro

LOCAL_PROGUARD_ENABLED := disabled

include $(BUILD_PACKAGE)
