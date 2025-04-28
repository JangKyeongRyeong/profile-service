import React, { useEffect, useState } from 'react';
import { fetchProfiles } from '../api/memberProfileApi';
import { useNavigate } from 'react-router-dom';

interface MemberProfile {
  id: number;
  name: string;
  viewCount: number;
  registeredAt: string;
}

const MemberProfileList = () => {
    const [profiles, setProfiles] = useState<MemberProfile[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
    fetchProfiles('name', 0)
      .then(res => {
        console.log('API 응답:', res.data);
        setProfiles(res.data.content);
      })
      .catch(err => console.error(err));
    }, []);

    // 프로필 클릭시 상세 페이지 이동
    const handleProfileClick = (id: number) => {
      navigate(`/profile/${id}`);
    };

    return (
    <div style={{ padding: '20px' }}>
      <h1>회원 프로필 목록 📄</h1>
      {profiles.map(profile => (
        <div key={profile.id} onClick={() => handleProfileClick(profile.id)} style={{ border: '1px solid gray', margin: '10px 0',padding: '10px'}}>
          <h2>{profile.name}</h2>
          <p>조회수: {profile.viewCount}</p>
          <p>등록일: {profile.registeredAt}</p>
        </div>
      ))}
    </div>
    );
};

export default MemberProfileList;
