import { render, screen, fireEvent } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import Dashboard from './index';

describe('Dashboard Page Component', () => {
  it('renders home page by default', () => {
    render(<Dashboard />);
    expect(screen.getByText(/good afternoon/i)).toBeInTheDocument();
  });

  it('switches page when sidebar nav item is clicked', () => {
    render(<Dashboard />);
    fireEvent.click(screen.getByText('Cash Accleration'));
    expect(screen.getByText('Place to create new cash kicks to run your business.')).toBeInTheDocument();
  });
});
