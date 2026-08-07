import React, { useState } from 'react';
import './App.css';
import { Sidebar } from './components/molecules/Sidebar';
import { CandidatesTable } from './components/molecules/CandidatesTable';
import type { Candidate } from './components/molecules/CandidatesTable';
import { CandidateDetails } from './components/molecules/CandidateDetails';
import { PreAdverseAction } from './components/molecules/PreAdverseAction';
import { PreviewNoticeModal } from './components/molecules/PreviewNoticeModal';

const initialCandidates: Candidate[] = [
  { id: '1', name: 'John Smith', adjudication: '-', status: 'CLEAR', location: 'Barrouallie', date: '2/22/2022' },
  { id: '2', name: 'Serene', adjudication: '-', status: 'CLEAR', location: 'Vänersborg', date: '3/13/2022' },
  { id: '3', name: 'Walsh', adjudication: '-', status: 'CONSIDER', location: 'Sukamanah', date: '7/2/2022' },
  { id: '4', name: 'Maurizia', adjudication: '-', status: 'CLEAR', location: 'Sukamanah', date: '2/20/2022' },
  { id: '5', name: 'Kendre', adjudication: '-', status: 'CLEAR', location: 'Beutong Ateuh', date: '5/19/2022' },
  { id: '6', name: 'Erastus', adjudication: '-', status: 'CLEAR', location: 'Höviyn Am', date: '12/1/2022' },
  { id: '7', name: 'Jereme', adjudication: '-', status: 'CONSIDER', location: 'Sharingol', date: '7/26/2022' },
  { id: '8', name: 'John Smith', adjudication: '-', status: 'CONSIDER', location: 'Lianyun', date: '5/28/2022' },
  { id: '9', name: 'Cari', adjudication: '-', status: 'CLEAR', location: 'Taboão da Serra', date: '5/23/2022' },
  { id: '10', name: 'Kimble', adjudication: '-', status: 'CONSIDER', location: 'Veseli nad Moravou', date: '8/24/2022' },
];

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
