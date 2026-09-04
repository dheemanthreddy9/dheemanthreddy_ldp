import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import Typography from './index';

describe('Typography Atom Component', () => {
  it('renders text content correctly', () => {
    render(<Typography variant="h1">Hello World</Typography>);
    expect(screen.getByText('Hello World')).toBeInTheDocument();
  });
});
