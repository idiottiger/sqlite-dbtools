// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tablenames.proto

package dbtools_proto;

public final class Tablenames {
  private Tablenames() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface TableNamesOrBuilder extends
      // @@protoc_insertion_point(interface_extends:dbtools_proto.TableNames)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated string name = 1;</code>
     */
    com.google.protobuf.ProtocolStringList
        getNameList();
    /**
     * <code>repeated string name = 1;</code>
     */
    int getNameCount();
    /**
     * <code>repeated string name = 1;</code>
     */
    String getName(int index);
    /**
     * <code>repeated string name = 1;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes(int index);
  }
  /**
   * Protobuf type {@code dbtools_proto.TableNames}
   */
  public static final class TableNames extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:dbtools_proto.TableNames)
      TableNamesOrBuilder {
    // Use TableNames.newBuilder() to construct.
    private TableNames(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private TableNames(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final TableNames defaultInstance;
    public static TableNames getDefaultInstance() {
      return defaultInstance;
    }

    public TableNames getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private TableNames(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                name_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000001;
              }
              name_.add(bs);
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          name_ = name_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Tablenames.internal_static_dbtools_proto_TableNames_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Tablenames.internal_static_dbtools_proto_TableNames_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              TableNames.class, Builder.class);
    }

    public static com.google.protobuf.Parser<TableNames> PARSER =
        new com.google.protobuf.AbstractParser<TableNames>() {
      public TableNames parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TableNames(input, extensionRegistry);
      }
    };

    @Override
    public com.google.protobuf.Parser<TableNames> getParserForType() {
      return PARSER;
    }

    public static final int NAME_FIELD_NUMBER = 1;
    private com.google.protobuf.LazyStringList name_;
    /**
     * <code>repeated string name = 1;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getNameList() {
      return name_;
    }
    /**
     * <code>repeated string name = 1;</code>
     */
    public int getNameCount() {
      return name_.size();
    }
    /**
     * <code>repeated string name = 1;</code>
     */
    public String getName(int index) {
      return name_.get(index);
    }
    /**
     * <code>repeated string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes(int index) {
      return name_.getByteString(index);
    }

    private void initFields() {
      name_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (int i = 0; i < name_.size(); i++) {
        output.writeBytes(1, name_.getByteString(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < name_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeBytesSizeNoTag(name_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getNameList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    protected Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static TableNames parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TableNames parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TableNames parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TableNames parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TableNames parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static TableNames parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static TableNames parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static TableNames parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static TableNames parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static TableNames parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(TableNames prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code dbtools_proto.TableNames}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:dbtools_proto.TableNames)
        TableNamesOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Tablenames.internal_static_dbtools_proto_TableNames_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Tablenames.internal_static_dbtools_proto_TableNames_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                TableNames.class, Builder.class);
      }

      // Construct using dbtools_proto.Tablenames.TableNames.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        name_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Tablenames.internal_static_dbtools_proto_TableNames_descriptor;
      }

      public TableNames getDefaultInstanceForType() {
        return TableNames.getDefaultInstance();
      }

      public TableNames build() {
        TableNames result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public TableNames buildPartial() {
        TableNames result = new TableNames(this);
        int from_bitField0_ = bitField0_;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          name_ = name_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.name_ = name_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof TableNames) {
          return mergeFrom((TableNames)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(TableNames other) {
        if (other == TableNames.getDefaultInstance()) return this;
        if (!other.name_.isEmpty()) {
          if (name_.isEmpty()) {
            name_ = other.name_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureNameIsMutable();
            name_.addAll(other.name_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        TableNames parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (TableNames) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private com.google.protobuf.LazyStringList name_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureNameIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          name_ = new com.google.protobuf.LazyStringArrayList(name_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getNameList() {
        return name_.getUnmodifiableView();
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public int getNameCount() {
        return name_.size();
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public String getName(int index) {
        return name_.get(index);
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes(int index) {
        return name_.getByteString(index);
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public Builder setName(
          int index, String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureNameIsMutable();
        name_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public Builder addName(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureNameIsMutable();
        name_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public Builder addAllName(
          Iterable<String> values) {
        ensureNameIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, name_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public Builder clearName() {
        name_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string name = 1;</code>
       */
      public Builder addNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureNameIsMutable();
        name_.add(value);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:dbtools_proto.TableNames)
    }

    static {
      defaultInstance = new TableNames(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:dbtools_proto.TableNames)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_dbtools_proto_TableNames_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_dbtools_proto_TableNames_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\020tablenames.proto\022\rdbtools_proto\"\032\n\nTab" +
      "leNames\022\014\n\004name\030\001 \003(\t"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_dbtools_proto_TableNames_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_dbtools_proto_TableNames_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_dbtools_proto_TableNames_descriptor,
        new String[] { "Name", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
