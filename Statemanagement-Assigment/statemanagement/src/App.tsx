import React, { useState } from 'react';
import './App.css';
import { Sidebar } from './components/molecules/Sidebar';
import { CandidatesTable } from './components/molecules/CandidatesTable';
import type { Candidate } from './components/molecules/CandidatesTable';
import { CandidateDetails } from './components/molecules/CandidateDetails';
import { PreAdverseAction } from './components/molecules/PreAdverseAction';
import { PreviewNoticeModal } from './components/molecules/PreviewNoticeModal';
import { initialCandidates } from './data/constants';


export const App: React.FC = () => {
  const [candidates, setCandidates] = useState<Candidate[]>(initialCandidates);
  const [currentPage, setCurrentPage] = useState<'page1' | 'page2' | 'page3'>('page1');
  const [selectedCandidate, setSelectedCandidate] = useState<Candidate | null>(null);
  const [showPreviewModal, setShowPreviewModal] = useState(false);
  const [selectedCharges, setSelectedCharges] = useState<string[]>([]);

  const handleSelectCandidate = (candidate: Candidate) => {
    setSelectedCandidate(candidate);
    setCurrentPage('page2');
  };

  const handleBackToDashboard = () => {
    setCurrentPage('page1');
  };

  const handlePreAdverseAction = () => {
    setCurrentPage('page3');
  };

  const handleBackToDetails = () => {
    setCurrentPage('page2');
  };

  const handlePreviewNotice = (charges: string[]) => {
    setSelectedCharges(charges);
    setShowPreviewModal(true);
  };

  const handleCloseModal = () => {
    setShowPreviewModal(false);
  };

  const handleSubmitNotice = () => {
    if (selectedCandidate) {
      setCandidates(prevCandidates => 
        prevCandidates.map(c => 
          c.id === selectedCandidate.id 
            ? { ...c, adjudication: 'ADVERSE ACTION', status: 'CONSIDER' } 
            : c
        )
      );
    }
    setShowPreviewModal(false);
    setCurrentPage('page1');
  };

  return (
    <div className="app-container">
      <Sidebar />

      {currentPage === 'page1' && (
        <CandidatesTable
          candidates={candidates}
          onSelectCandidate={handleSelectCandidate}
        />
      )}

      {currentPage === 'page2' && selectedCandidate && (
        <CandidateDetails
          candidate={selectedCandidate}
          onBack={handleBackToDashboard}
          onPreAdverseAction={handlePreAdverseAction}
        />
      )}

      {currentPage === 'page3' && selectedCandidate && (
        <PreAdverseAction
          candidate={selectedCandidate}
          onBack={handleBackToDetails}
          onPreviewNotice={handlePreviewNotice}
        />
      )}

      {showPreviewModal && selectedCandidate && (
        <PreviewNoticeModal
          candidate={selectedCandidate}
          selectedCharges={selectedCharges}
          onClose={handleCloseModal}
          onSubmit={handleSubmitNotice}
        />
      )}
    </div>
  );
};

export default App;
