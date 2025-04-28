import React, { useEffect, useState } from 'react';
import { fetchProfiles } from '../api/memberProfileApi';

interface MemberProfile {
  id: number;
  name: string;
  viewCount: number;
  registeredAt: string;
}

const MemberProfileList = () => {
  const [profiles, setProfiles] = useState<MemberProfile[]>([]);

  useEffect(() => {
    fetchProfiles('name', 0)
      .then(res => {
        console.log('API 응답:', res.data);
        setProfiles(res.data.content);  // 여기 중요! content를 꺼내야함
      })
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      {profiles.map(profile => (
        <div key={profile.id}>
          <h2>{profile.name}</h2>
          <p>조회수: {profile.viewCount}</p>
          <p>등록일: {profile.registeredAt}</p>
        </div>
      ))}
    </div>
  );
};

export default MemberProfileList;
