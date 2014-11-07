LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_ARM_MODE := arm
LOCAL_CPPFLAGS += -frtti
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE := libdbtools
#LOCAL_MODULE_TAGS := optional
LOCAL_CPP_EXTENSION := .cc .cpp

LOCAL_SRC_FILES := \
	protobuf/google/protobuf/io/coded_stream.cc \
	protobuf/google/protobuf/stubs/common.cc \
	protobuf/google/protobuf/descriptor.cc \
	protobuf/google/protobuf/descriptor.pb.cc \
	protobuf/google/protobuf/descriptor_database.cc \
	protobuf/google/protobuf/dynamic_message.cc \
	protobuf/google/protobuf/extension_set.cc \
	protobuf/google/protobuf/extension_set_heavy.cc \
	protobuf/google/protobuf/generated_message_reflection.cc \
	protobuf/google/protobuf/generated_message_util.cc \
	protobuf/google/protobuf/io/gzip_stream.cc \
	protobuf/google/protobuf/io/strtod.cc \
	protobuf/google/protobuf/compiler/importer.cc \
	protobuf/google/protobuf/message.cc \
	protobuf/google/protobuf/message_lite.cc \
	protobuf/google/protobuf/stubs/once.cc \
	protobuf/google/protobuf/stubs/atomicops_internals_x86_msvc.cc \
	protobuf/google/protobuf/compiler/parser.cc \
	protobuf/google/protobuf/io/printer.cc \
	protobuf/google/protobuf/reflection_ops.cc \
	protobuf/google/protobuf/repeated_field.cc \
	protobuf/google/protobuf/service.cc \
	protobuf/google/protobuf/stubs/structurally_valid.cc \
	protobuf/google/protobuf/stubs/strutil.cc \
	protobuf/google/protobuf/stubs/substitute.cc \
	protobuf/google/protobuf/text_format.cc \
	protobuf/google/protobuf/io/tokenizer.cc \
	protobuf/google/protobuf/unknown_field_set.cc \
	protobuf/google/protobuf/wire_format.cc \
	protobuf/google/protobuf/wire_format_lite.cc \
	protobuf/google/protobuf/io/zero_copy_stream.cc \
	protobuf/google/protobuf/io/zero_copy_stream_impl.cc \
	protobuf/google/protobuf/io/zero_copy_stream_impl_lite.cc \
	protobuf/google/protobuf/stubs/stringprintf.cc \
	DBTools.cpp \
	sqlite/sqlite3.c \
	proto/tablenames.pb.cc

LOCAL_C_INCLUDES := $(LOCAL_PATH) \
	$(LOCAL_PATH)/protobuf/google \
	$(LOCAL_PATH)/protobuf \
	$(LOCAL_PATH)/protobuf/google/protobuf \
	$(LOCAL_PATH)/sqlite \
	$(LOCAL_PATH)/proto

LOCAL_LDLIBS := -llog -pthread

include $(BUILD_SHARED_LIBRARY)	