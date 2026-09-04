import { render, screen } from '@testing-library/react';
import { describe, it, expect } from 'vitest';
import Icon from './index';

describe('Icon Atom Component', () => {
  it('renders icon image with src and alt', () => {
    render(<Icon src="icon.svg" alt="Search Icon" />);
    const img = screen.getByAltText('Search Icon');
    expect(img).toBeInTheDocument();
    expect(img).toHaveAttribute('src', 'icon.svg');
  });
});
