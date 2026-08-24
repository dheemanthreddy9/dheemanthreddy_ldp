import React, { useState } from 'react';
import './App.css';
import { Sidebar } from './components/organisms/Sidebar';
import { CandidatesTable } from './components/organisms/CandidatesTable';
import type { Candidate } from './components/organisms/CandidatesTable';
import { CandidateDetails } from './components/organisms/CandidateDetails';
import { PreAdverseAction } from './components/organisms/PreAdverseAction';
import { PreviewNoticeModal } from './components/organisms/PreviewNoticeModal';
import { initialCandidates, ADJUDICATION_ADVERSE_ACTION, STATUS_CONSIDER } from './data/constants';

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
      setCandidates((prevCandidates) =>
        prevCandidates.map((c) =>
          c.id === selectedCandidate.id
            ? { ...c, adjudication: ADJUDICATION_ADVERSE_ACTION, status: STATUS_CONSIDER }
            : c
        )
      );
    }
    setShowPreviewModal(false);
    setCurrentPage('page1');
  };

  const handleNavigate = (page: string) => {
    if (page === 'page1' || page === 'page3') {
      setCurrentPage(page as 'page1' | 'page3');
    }
  };

  return (
    <div className="app-container">
      <Sidebar activePage={currentPage} onNavigate={handleNavigate} />

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

      {currentPage === 'page3' && (
        <PreAdverseAction
          candidate={selectedCandidate || candidates[0]}
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
