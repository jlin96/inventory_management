export const Footer = () => {

    const currentYear = new Date().getFullYear();

  return (
    <>
      <hr />
        <p>
          Jesse's Management INC.<br/>
          Copyright © {currentYear} <br/>
          All rights reserved.
        </p>
    </>
  )
}