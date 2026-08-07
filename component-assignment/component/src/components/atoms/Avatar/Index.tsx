interface AvatarProps {
  src: string;
  alt?: string;
  size?: number;
}

const Avatar = ({
  src,
  alt = "Avatar",
  size = 45,
}: AvatarProps) => {
  return (
    <img
      src={src}
      alt={alt}
      width={size}
      height={size}
      className="avatar"
    />
  );
};

export default Avatar;
