import React from 'react';
import { useNavigate } from 'react-router-dom';

interface MemberProfileItemProps {
  id: number;
  name: string;
  viewCount: number;
  createdAt: string;
}

const MemberProfileItem: React.FC<MemberProfileItemProps> = ({ id, name, viewCount, createdAt }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/members/${id}`);
  };

  return (
    <div onClick={handleClick} style={{ border: '1px solid gray', margin: '8px', padding: '8px', cursor: 'pointer' }}>
      <h3>{name}</h3>
      <p>조회수: {viewCount}</p>
      <p>등록일: {createdAt}</p>
    </div>
  );
};

export default MemberProfileItem;
