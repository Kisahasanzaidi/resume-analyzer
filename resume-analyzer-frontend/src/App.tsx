import React, { useEffect, useState } from 'react';
import UploadResume from './components/UploadResume';
import './index.css';

const App: React.FC = () => {
  const [resumes, setResumes] = useState<any[]>([]);

  const handleUpload = (resume: any) => {
    setResumes((prev) => [...prev, resume]);
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-4xl mx-auto bg-white shadow-lg rounded-lg p-6">
        <h1 className="text-4xl font-bold text-center text-indigo-600 mb-6">Resume Analyzer</h1>
        <UploadResume onUpload={handleUpload} />
      </div>
    </div>
  );
};

export default App;
