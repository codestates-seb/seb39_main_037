import React from "react";
import Pagination from "react-js-pagination";
import styled from "styled-components";

interface IPaginationProps {
  activePage: number; // 현재페이지
  itemsCountPerPage?: number; // 한 페이지당 보여지는 게시글 수
  totalItemsCount: number;
  onChange: (page: number) => void;
  pageRangeDisplayed?: number; // n쪽씩 페이지 li를 넘긴다.
}
const PaginationForm = ({
  activePage,
  itemsCountPerPage = 10,
  totalItemsCount,
  onChange: handleChange,
  pageRangeDisplayed = 5,
}: IPaginationProps) => {
  return (
    <PaginationWrapper>
      <Pagination
        activePage={activePage}
        itemsCountPerPage={itemsCountPerPage}
        totalItemsCount={totalItemsCount}
        pageRangeDisplayed={5}
        onChange={handleChange}
        prevPageText="<"
        nextPageText=">"
      />
    </PaginationWrapper>
  );
};

export default PaginationForm;

const PaginationWrapper = styled.div`
  margin-bottom: 20px;
  .pagination {
    display: flex;
    justify-content: center;
    margin-top: 15px;
  }

  ul {
    list-style: none;
    padding: 0;
  }

  ul.pagination li {
    display: inline-block;
    width: 30px;
    height: 30px;
    border: 1px solid var(--main-yellow);
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1rem;
  }

  ul.pagination li:first-child {
    border-radius: 5px 0 0 5px;
  }

  ul.pagination li:last-child {
    border-radius: 0 5px 5px 0;
  }

  ul.pagination li a {
    text-decoration: none;
    color: var(--main-yellow);
    font-size: 1rem;
  }

  ul.pagination li.active a {
    color: white;
  }

  ul.pagination li.active {
    background-color: var(--main-yellow);
  }

  ul.pagination li a:hover,
  ul.pagination li a.active {
    color: var(--yellow-background);
  }

  .page-selection {
    width: 48px;
    height: 30px;
    color: var(--main-yellow);
  }
`;
