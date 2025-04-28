import React, { useEffect, useState } from 'react';
import { fetchProfiles } from '../api/memberProfileApi';
import { useNavigate } from 'react-router-dom';

interface MemberProfile {
  id: number;
  name: string;
  viewCount: number;
  registeredAt: string;
}

interface ApiResponse {
  content: MemberProfile[];
  totalPages: number;
  number: number; // 현재 페이지
}

const MemberProfileList = () => {
    const [profiles, setProfiles] = useState<MemberProfile[]>([]);
    const [page, setPage] = useState(0);    //현재 페이지
    const [totalPages, setTotalPages] = useState(0); //전체 페이지 수
    const navigate = useNavigate();

    const loadProfiles = (pageNumber: number) => {
        fetchProfiles('name', pageNumber)
            .then(res => {
                console.log('API 응답:', res.data);
                setProfiles(res.data.content);
                setTotalPages(res.data.totalPages);
                setPage(res.data.number);
            })
            .catch(err => console.error(err));
    };

    useEffect(() => {loadProfiles(page)}, [page]); // page가 바뀔때마다 호출


    const goToDetail = (id: number) => {navigate(`/profile/${id}`)};

    const handlePrev = () => {
        if(page > 0) {
            setPage(prev => prev -1);
        }
    }

    const handleNext = () => {
        if(page < totalPages-1) {
            setPage(prev => prev +1);
        }
    }

    return (
        <div style={{ padding: '20px' }}>
          <h1>회원 프로필 목록 📄</h1>

          {profiles.map(profile => (
                                        <div key={profile.id} style={{ cursor: 'pointer' }} onClick={() => goToDetail(profile.id)}>
                                          <h2>{profile.name}</h2>
                                          <p>조회수: {profile.viewCount}</p>
                                          <p>등록일: {profile.registeredAt}</p>
                                        </div>
          ))}

          {/* 페이지네이션 버튼 */}
          <div style={{ marginTop: '20px' }}>
            <button onClick={handlePrev} disabled={page === 0}>
              이전
            </button>
            <span style={{ margin: '0 10px' }}>
              {page + 1} / {totalPages}
            </span>
            <button onClick={handleNext} disabled={page === totalPages - 1}>
              다음
            </button>
          </div>
        </div>
    );
};

export default MemberProfileList;
