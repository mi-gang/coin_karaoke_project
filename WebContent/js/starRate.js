const starSize = 15,
  maxStar = 5,
  gutter = 0; //별 크기, 별 개수
let maskMax = 0; //오버레이 마스크 최대 너비
function drawStarRate(rating, overlay, rate) {
  //별 이미지 SVG 개수만큼 생성
  for (let i = 0; i < maxStar; i++) {
    let el = document.createElement("div");
    //el.classList.add('star');
    el.style.width = starSize + "px";
    el.style.height = starSize + "px";
    el.style.marginRight = gutter + "px";
    //인라인 SVG 이미지 부착
    el.innerHTML =
      '<svg viewBox="0 0 15 13" xmlns="http://www.w3.org/2000/svg"><g clip - path="url(#clip0_42_1908)" ><path d="M7.80197 9.33193L11.3641 11.3475L10.4188 7.54873L13.5659 4.99281L9.42163 4.66318L7.80197 1.08057L6.18232 4.66318L2.03809 4.99281L5.18517 7.54873L4.23989 11.3475L7.80197 9.33193Z" fill="none" class="starcolor" /></g ><defs><clipPath id="clip0_42_1908"><rect width="13.8333" height="12.9688" fill="white" transform="translate(0.885254)" /></clipPath></defs></svg >';
    rating.appendChild(el);
  }

  maskMax = parseInt(starSize * maxStar + gutter * (maxStar - 1)); //최대 마스크 너비 계산
  setRating(overlay, rate); //초기 별점 마킹


}
//별점 마킹 함수
function setRating(overlay, val) {
    overlay.style.width =
      parseInt(maskMax - val * starSize - Math.floor(val) * gutter) + "px"; //마스크 크기 변경해서 별점 마킹
  }
/*const starSize = 15,
  maxStar = 5,
  gutter = 0; //별 크기, 별 개수
let maskMax = 0; //오버레이 마스크 최대 너비
window.addEventListener("DOMContentLoaded", () => {
  //별 이미지 SVG 개수만큼 생성
  for (let i = 0; i < maxStar; i++) {
    let el = document.createElement("div");
    //el.classList.add('star');
    el.style.width = starSize + "px";
    el.style.height = starSize + "px";
    el.style.marginRight = gutter + "px";
    //인라인 SVG 이미지 부착
    el.innerHTML =
      '<svg viewBox="0 0 15 13" xmlns="http://www.w3.org/2000/svg"><g clip - path="url(#clip0_42_1908)" ><path d="M7.80197 9.33193L11.3641 11.3475L10.4188 7.54873L13.5659 4.99281L9.42163 4.66318L7.80197 1.08057L6.18232 4.66318L2.03809 4.99281L5.18517 7.54873L4.23989 11.3475L7.80197 9.33193Z" fill="none" class="starcolor" /></g ><defs><clipPath id="clip0_42_1908"><rect width="13.8333" height="12.9688" fill="white" transform="translate(0.885254)" /></clipPath></defs></svg >';
    document.querySelector(".rating").appendChild(el);
  }

  maskMax = parseInt(starSize * maxStar + gutter * (maxStar - 1)); //최대 마스크 너비 계산
  setRating(3.5); //초기 별점 마킹


  //별점 마킹 함수
  function setRating(val = 0) {
    document.querySelector(".overlay").style.width =
      parseInt(maskMax - val * starSize - Math.floor(val) * gutter) + "px"; //마스크 크기 변경해서 별점 마킹
  }

  //마우스 클릭 별점 변경 이벤트 리스너
  document.querySelector(".rating").addEventListener("click", (e) => {
    //closest()로 .rating 요소의 왼쪽 위치를 찾아서 현재 클릭한 위치에서 빼야 상대 클릭 위치를 찾을 수 있음.
    const maskSize = parseInt(
      maskMax -
        parseInt(e.clientX) +
        e.target.closest(".rating").getBoundingClientRect().left
    ); //클릭한 위치 기준 마스크 크기 재계산
    document.querySelector(".overlay").style.width = maskSize + "px"; //오버레이 마스크 크기 변경해서 별점 마킹
    document.querySelector("input[name=ratevalue]").value =
      Math.floor((maskMax - maskSize) / (starSize + gutter)) +
      parseFloat(
        (((maskMax - maskSize) % (starSize + gutter)) / starSize).toFixed(1)
      );
  });
}); */