﻿using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using Amigos.DataAccessLayed;
using Amigos.Models;

namespace Amigos.Controllers.API
{
    public class AmigoController : ApiController
    {
        private AmigoDBContext db = new AmigoDBContext();

        // GET: api/amigo (http://localhost:54321/api/amigo)
        public IQueryable<Amigo> GetAmigos()
        {
            return db.Amigos;
        }

        // GET: api/amigo/5 (http://localhost:54321/api/amigo/2)
        [ResponseType(typeof(Amigo))]
        public IHttpActionResult GetAmigo(int id)
        {
            Amigo amigo = db.Amigos.Find(id);
            if (amigo == null)
            {
                return NotFound();
            }

            return Ok(amigo);
        }
    
        // PUT: api/amigo/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutAmigo(int id, Amigo amigo)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != amigo.ID)
            {
                return BadRequest();
            }

            db.Entry(amigo).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AmigoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        //PUT: api/amigo/Marta
        [ResponseType(typeof(void))]
        public IHttpActionResult PutAmigo(String name, Amigo amigo)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            List<Amigo> list = new List<Amigo>();
            foreach (Amigo friend in db.Amigos)
            {
                if (friend.name==amigo.name)
                {
                    list.Add(friend);
                    break;
                }
            }
            if (!list.Any())
            {
                return NotFound();
            }
            amigo.ID = list[0].ID;
            db.Entry(amigo).State = EntityState.Modified;
            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AmigoExists(amigo.ID))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/amigo
        [ResponseType(typeof(Amigo))]
        public IHttpActionResult PostAmigo(Amigo amigo)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Amigos.Add(amigo);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = amigo.ID }, amigo);
        }

        // DELETE: api/amigo/5
        [ResponseType(typeof(Amigo))]
        public IHttpActionResult DeleteAmigo(int id)
        {
            Amigo amigo = db.Amigos.Find(id);
            if (amigo == null)
            {
                return NotFound();
            }

            db.Amigos.Remove(amigo);
            db.SaveChanges();

            return Ok(amigo);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool AmigoExists(int id)
        {
            return db.Amigos.Count(e => e.ID == id) > 0;
        }
    }
}