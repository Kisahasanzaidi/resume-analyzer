import React from 'react';
import { Upload, Button, message } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

const UploadResume = ({ onUpload }) => {
  const props = {
    beforeUpload: async (file) => {
      const formData = new FormData();
      formData.append('file', file);

      try {
        const response = await fetch('http://localhost:8080/api/resumes/upload', {
          method: 'POST',
          body: formData,
        });

        if (!response.ok) throw new Error('Upload failed');

        const data = await response.json(); // Expecting Resume object from backend
        onUpload(data); // Send uploaded resume data to parent component
        message.success(`${file.name} uploaded successfully.`);
      } catch (error) {
        console.error(error);
        message.error(`${file.name} upload failed.`);
      }

      return false; // Prevent auto-upload by Ant Design
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
