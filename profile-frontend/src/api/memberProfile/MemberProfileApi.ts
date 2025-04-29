import axios from 'axios';

// memberProfileList 조회
export const fetchProfiles = (sort: string, page: number) => {
  return axios.get(`/api/member-profiles/members?sort=${sort}&page=${page}`);
};

// 상세 조회
export const fetchProfileDetail = (id: number) => {
    return axios.get(`/api/member-profiles/members/${id}`);
}

// 조회수 업데이트
export const increaseViewCount = (id: number) => {
    return axios.put(`/api/member-profiles/members/${id}/view-count`);
}

