import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import Header from './index';

describe('Header Organism Component', () => {
  it('renders default greeting and date when no props passed', () => {
    render(<Header />);
    expect(screen.getByText(/good afternoon/i)).toBeInTheDocument();
  });

  it('renders custom title and subtitle when provided', () => {
    render(<Header title="Cash Acceleration" subtitle="Manage your cash" />);
    expect(screen.getByText('Cash Acceleration')).toBeInTheDocument();
    expect(screen.getByText('Manage your cash')).toBeInTheDocument();
  });

  it('opens profile dropdown menu on click', () => {
    render(<Header />);
    const trigger = screen.getByRole('img', { name: /kane cooper/i });
    fireEvent.click(trigger);
    expect(screen.getByText(/edit profile/i)).toBeInTheDocument();
  });
});
