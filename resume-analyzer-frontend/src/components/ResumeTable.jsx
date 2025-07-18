import React from 'react';
import { Table } from 'antd';

const ResumeTable = ({ resumes }) => {
  const columns = [
    { title: 'File Name', dataIndex: 'name', key: 'name' },
    { title: 'Type', dataIndex: 'type', key: 'type' },
    { title: 'Size (KB)', dataIndex: 'size', key: 'size', render: size => (size / 1024).toFixed(2) },
  ];

  return (
    <Table
      className="mt-6"
      dataSource={resumes.map((r, i) => ({ ...r, key: i }))}
      columns={columns}
      pagination={false}
    />
  );
};

export default ResumeTable;
