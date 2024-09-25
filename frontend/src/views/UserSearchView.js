import { Container } from '@mui/material'
import UserList from '../components/UserList/UserList'

const UserSearchView = () => {
  return (
    <>
      <Container maxWidth='md'>
        <UserList />
      </Container>
    </>
  )
}

export default UserSearchView
