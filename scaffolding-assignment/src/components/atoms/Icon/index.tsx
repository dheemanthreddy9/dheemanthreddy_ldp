import React from 'react';

export interface IconProps {
  src: string;
  alt: string;
  className?: string;
}

const Icon: React.FC<IconProps> = ({ src, alt, className }) => {
  return <img src={src} alt={alt} className={className} />;
};

export default Icon;
