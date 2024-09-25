import { Avatar, Box, Button, Divider, List, ListItem, Paper, Typography } from '@mui/material'

const testUsers = [
  {
    id: 1,
    first_name: 'Jurin',
    last_name: 'Asaya',
    image_url: 'https://kpopping.com/documents/c0/0/800/240219-XG-Twitter-Update-Jurin-documents-2.jpeg?v=482bf',
    username: 'xg_jurin'
  },
  {
    id: 2,
    first_name: 'Maya',
    last_name: 'Kawachi',
    image_url: 'https://kpopping.com/documents/ba/0/2744/221012-XG-Twitter-Update-Maya-documents-1.jpeg?v=bbab5',
    username: 'xg_maya'
  },
  {
    id: 3,
    first_name: 'Cocona',
    last_name: 'Akiyama',
    image_url: 'https://kpopping.com/documents/34/1/1366/230211-XG-Cocona-Twitter-Update-documents-2.jpeg?v=73859',
    username: 'xg_cocona'
  },
  {
    id: 4,
    first_name: 'Amy',
    last_name: 'Harvey',
    image_url: 'https://pbs.twimg.com/media/Fc8fBvGagAIyMWg?format=jpg&name=large',
    username: 'xg_harvey'
  },
  {
    id: 5,
    first_name: 'Juria',
    last_name: 'Ueda',
    image_url: 'https://kpopping.com/documents/7c/3/1080/240813-XG-Instagram-Update-with-JURIA-documents-3.jpeg?v=50274',
    username: 'xg_juria'
  },
  {
    id: 6,
    first_name: 'Chisa',
    last_name: 'Kondou',
    image_url: 'https://kpopping.com/documents/a2/4/800/230215-XG-Chisa-Twitter-Update-documents-1.jpeg?v=593d1',
    username: 'xg_chisa'
  },
  {
    id: 7,
    first_name: 'Hinata',
    last_name: 'Soraha',
    image_url: 'https://kpopping.com/documents/98/1/800/220721-XG-Hinata-Twitter-Update-documents-1.jpeg?v=482bf',
    username: 'xg_hinata'
  }
]

const UserList = () => {
  return (
    <>
      <Paper sx={{ px: 2, py: 1, borderRadius: 3 }}>
        <List>
          {
            testUsers.map((user, i) => (
              <div key={user.id}>
                <ListItem display='flex' sx={{ py: 2 }}>
                  <Avatar src={user.image_url} sx={{ width: 50, height: 50 }} />
                  <Box sx={{ px: 2, display: 'flex', flexDirection: 'column', justifyContent: 'center', flexGrow: 1 }}>
                    <Typography variant='body1' sx={{ fontWeight: 500 }}>{user.first_name} {user.last_name}</Typography>
                    <Typography variant='body2' color='text.grey'>@{user.username}</Typography>
                  </Box>
                  <Box sx={{ display: 'flex', alignItems: 'center' }}>
                    <Button>Follow</Button>
                  </Box>
                </ListItem>
                {i < testUsers.length - 1 && <Divider variant='middle' component='li' />}
              </div>
            ))
          }
        </List>
      </Paper>
    </>
  )
}

export default UserList
