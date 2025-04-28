import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { fetchProfileDetail, increaseViewCount } from '../api/memberProfileApi';

interface MemberProfile {
  id: number;
  name: string;
  viewCount: number;
  createdAt: string;
}

const MemberProfileDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [profile, setProfile] = useState<MemberProfile | null>(null);

  useEffect(() => {
    if (id) {
      fetchProfileDetail(Number(id))
        .then((res) => setProfile(res.data))
        .catch((err) => console.error(err));

      increaseViewCount(Number(id))
        .then(() => console.log('조회수 업데이트 완료'))
        .catch((err) => console.error(err));
    }
  }, [id]);

  if (!profile) return <div>Loading...</div>;

  return (
    <div>
      <button onClick={() => navigate(-1)}>뒤로가기</button>
      <h1>회원 프로필 상세 보기 🧑‍💻</h1>
      <h2>{profile.name}</h2>
      <p>조회수: {profile.viewCount}</p>
      <p>등록일: {profile.createdAt}</p>
    </div>
  );
};

export default MemberProfileDetail;
