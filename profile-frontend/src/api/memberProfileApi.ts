import axios from 'axios';

export const fetchProfiles = (sort: string, page: number) => {
  return axios.get(`/api/member-profiles/members?sort=${sort}&page=${page}`);
};
