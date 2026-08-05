interface AvatarProps {
  src: string;
  alt?: string;
}

const Avatar = ({
  src,
  alt = "Avatar",
}: AvatarProps) => {
  return (
    <img
      src={src}
      alt={alt}
      className="avatar"
    />
  );
};

export default Avatar;