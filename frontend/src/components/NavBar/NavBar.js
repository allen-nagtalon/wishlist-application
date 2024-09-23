import { useEffect, useState } from 'react'
import { Avatar, Box, Button, Container, IconButton, Menu, MenuItem, Toolbar, Typography } from '@mui/material'
import AppBar from '@mui/material/AppBar'
import ApiInstance from '../../services/ApiInstance'
import { Link, NavLink } from 'react-router-dom'

const pages = [
  {
    title: 'Dashboard',
    to: '/'
  },
  {
    title: 'My Wishlists',
    to: '/wishlists'
  }
]

function NavBar ({ user, setUser }) {
  const [anchorElUser, setAnchorElUser] = useState(null)

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget)
  }

  const handleCloseUserMenu = (event) => {
    setAnchorElUser(null)
  }

  const logout = () => {
    ApiInstance.defaults.headers.Authorization = ''
    setUser(null)
  }

  useEffect(() => {
    console.log(user)
  }, [])

  return (
    <AppBar id='app-bar' position='fixed' elevation={0} color='light' sx={{ py: 1 }}>
      <Container maxWidth='xl'>
        <Toolbar>
          <Typography
            variant='logo'
            component='a'
            href='/'
            sx={{
              flexGrow: 1,
              display: { md: 'flex' },
              color: 'text.dark',
              textDecoration: 'none'
            }}
          >
            WISHLIST.IO
          </Typography>

          <Box sx={{ flexGrow: 0, display: { md: 'flex' } }}>
            {pages.map((page) => (
              <Button key={page.title} sx={{ color: 'text.dark', ml: 3 }}>
                <NavLink to={page.to} style={{ textDecoration: 'none', color: 'inherit' }}>
                  {page.title}
                </NavLink>
              </Button>
            ))}
          </Box>

          {user
            ? <>
              <Box sx={{ flexGrow: 0, ml: 3 }}>
                <IconButton onClick={handleOpenUserMenu}>
                  <Avatar src='https://kpopping.com/documents/0f/1/800/240219-XG-Twitter-Update-Jurin-documents-1.jpeg?v=2f72c' />
                </IconButton>
                <Menu
                  sx={{ mt: '45px' }}
                  id='user-menu-appbar'
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right'
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right'
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  <MenuItem onClick={logout}>
                    <Typography>Log Out</Typography>
                  </MenuItem>
                </Menu>
              </Box>
            </>
            : <>
              <Button sx={{ color: 'text.dark', ml: 3 }}>
                <NavLink to='/login' style={{ textDecoration: 'none', color: 'inherit' }}>
                  Log In
                </NavLink>
              </Button>
            </>
          }
        </Toolbar>
      </Container>
    </AppBar>
  )
}

export default NavBar
