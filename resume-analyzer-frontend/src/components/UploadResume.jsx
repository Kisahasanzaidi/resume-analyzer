import React from 'react';
import { Upload, Button, message } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

const UploadResume = ({ onUpload }) => {
  const props = {
    beforeUpload: (file) => {
      onUpload({
        name: file.name,
        size: file.size,
        type: file.type,
      });
      message.success(`${file.name} uploaded successfully.`);
      return false; // prevent automatic upload
    },
    showUploadList: false,
  };

  return (
    <div className="mb-6">
      <Upload {...props}>
        <Button icon={<UploadOutlined />}>Click to Upload Resume</Button>
      </Upload>
    </div>
  );
};

export default UploadResume;
