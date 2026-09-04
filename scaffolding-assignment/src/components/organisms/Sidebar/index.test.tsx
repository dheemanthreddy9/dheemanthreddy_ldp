import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect, vi } from 'vitest';
import Sidebar from './index';

describe('Sidebar Organism Component', () => {
  it('renders brand name and navigation items', () => {
    const handleNavigate = vi.fn();
    render(<Sidebar activePage="home" onNavigate={handleNavigate} />);
    expect(screen.getByText('Seeder')).toBeInTheDocument();
    expect(screen.getByText('Home')).toBeInTheDocument();
    expect(screen.getByText('Cash Accleration')).toBeInTheDocument();
  });

  it('calls onNavigate when item is clicked', () => {
    const handleNavigate = vi.fn();
    render(<Sidebar activePage="home" onNavigate={handleNavigate} />);
    fireEvent.click(screen.getByText('Cash Accleration'));
    expect(handleNavigate).toHaveBeenCalledWith('cashAcceleration');
  });
});
